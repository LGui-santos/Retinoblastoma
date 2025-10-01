package br.com.retinoblastoma.client.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.retinoblastoma.client.security.provider.UserAuthenticationProvider;
import br.com.retinoblastoma.client.service.impl.TopicRequestServiceImpl;
import br.com.retinoblastoma.client.service.impl.TopicResponseServiceImpl;
import br.com.retinoblastoma.model.dto.ResponseDto;
import br.com.retinoblastoma.model.dto.TopicRequestDto;
import br.com.retinoblastoma.model.dto.TopicResponseDto;
import br.com.retinoblastoma.model.dto.UserDto;

@Controller
@RequestMapping("/forum")
public class ForumController {

	@Autowired
	private TopicResponseServiceImpl topicResponseServiceImpl;

	@Autowired
	private TopicRequestServiceImpl topicRequestServiceImpl;

	@Autowired
	private TopicResponseServiceImpl responseServiceImpl;

	@Autowired
	private UserAuthenticationProvider authProvider;

	@GetMapping("/topics")
	public String topics(Model model) {

		// busca todos os tópicos
		List<TopicResponseDto> topics = topicResponseServiceImpl.readAllTopicResponseDto();

		// adiciona no model com nome plural
		model.addAttribute("topics", topics);

		// retorna a view
		return "forum/topics";
	}

	@GetMapping("/new-topic")
	public String getNewTopic(TopicRequestDto topicRequestDto, Model model) {

		UserDto authenticatedUser = authProvider.getAuthenticatedUser();
		topicRequestDto.setUserId(authenticatedUser.getId());

		model.addAttribute("topicRequestDto", topicRequestDto);

		return "forum/new-topic";
	}

	@GetMapping("/discussion")
	public String getDiscussion(TopicRequestDto topicRequestDto, TopicResponseDto topicResponseDto) {
		return "/forum/discussion";
	}

	@GetMapping("/topic/{id}")
	public String discussion(@PathVariable Long id, Model model) {
		TopicResponseDto topic = topicResponseServiceImpl.readById(id);

		model.addAttribute("topicResponseDto", topic);
		ResponseDto response = new ResponseDto();
		response.setTopicId(id);
		model.addAttribute("response", response); // adiciona objeto vazio pro form

		if (topic.getImage() != null) {
			String mimeType = getImageMimeType(topic.getImage());
			String image = "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(topic.getImage());
            System.out.println("Mostrando os bytes da imagem: " + image);
			model.addAttribute("image", image);
		}

		return "forum/discussion";
	}

	@PostMapping("/response")
	public String sendResponse(ResponseDto responseDto, Model model) {

		TopicResponseDto topicResponseDto = responseServiceImpl.sendResponse(responseDto);

		model.addAttribute("topicResponseDto", topicResponseDto);
		ResponseDto response = new ResponseDto();
		response.setTopicId(topicResponseDto.getId());
		model.addAttribute("response", response);

		if (topicResponseDto.getImage() != null) {
			String mimeType = getImageMimeType(topicResponseDto.getImage());
			String image = "data:" + mimeType + ";base64,"
					+ Base64.getEncoder().encodeToString(topicResponseDto.getImage());
			model.addAttribute("image", image);
		}
		return "forum/discussion";
	}

	@GetMapping("/topic-list/{id}")
	public String getTopicList(@PathVariable Long id, Model model) {
		TopicResponseDto topic = topicResponseServiceImpl.readById(id);

		model.addAttribute("topic", topic);

		return "forum/topics";
	}

	@PostMapping("/new-topic")
	public String createTopic(TopicRequestDto topicRequestDto, @RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirect) {

		try {
			// Se quiser, valide se o arquivo veio vazio:
			if (file.isEmpty()) {
				redirect.addFlashAttribute("message", "Por favor, selecione uma imagem para o tópico.");
				return "redirect:/forum/new-topic";
			}

			// Chamar o service para enviar topic + file para a API
			TopicResponseDto topicResponse = topicRequestServiceImpl.createTopic(topicRequestDto, file);

			if (topicResponse == null) {
				redirect.addFlashAttribute("message", "Erro ao criar tópico. Tente novamente.");
				return "redirect:/forum/new-topic";
			}

			return discussion(topicResponse.getId(), model); // redireciona para a lista de tópicos
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("message", "Erro inesperado.");
			return "redirect:/forum/new-topic";
		}

//		TopicResponseDto topicRequestDtoResponse = topicRequestServiceImpl.createTopic(topicRequestDto);
//
//		if (topicRequestDtoResponse == null) {
//			return "redirect:/account/register?serverError";
//		}

//		model.addAttribute("topicResponseDto", topicRequestDtoResponse);

//		return "/forum/discussion";

//		return topics(model);
	}

	@PostMapping("/response/delete/{id}")
	public String deleteResponse(@PathVariable Long id, @RequestParam Long topicId,
			RedirectAttributes redirectAttributes) {

		topicResponseServiceImpl.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "Resposta removida com sucesso");
		return "redirect:/forum/topic/" + topicId;
	}

	@PostMapping("/topic/delete/{id}")
	public String deleteTopic(@PathVariable Long id, RedirectAttributes redirectAttributes, Model model) {

		topicRequestServiceImpl.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "Resposta removida com sucesso");
		return topics(model);
	}

	public String getImageMimeType(byte[] imageData) {
		if (imageData == null || imageData.length < 8) {
			return "image/jpeg"; // default
		}

		// PNG: começa com 89 50 4E 47
		if (imageData[0] == (byte) 0x89 && imageData[1] == (byte) 0x50 && imageData[2] == (byte) 0x4E
				&& imageData[3] == (byte) 0x47) {
			return "image/png";
		}

		// JPEG: começa com FF D8
		if (imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8) {
			return "image/jpeg";
		}

		// GIF: começa com GIF87a ou GIF89a
		if (imageData.length >= 6) {
			String header = new String(imageData, 0, 6, StandardCharsets.US_ASCII);
			if (header.startsWith("GIF87a") || header.startsWith("GIF89a")) {
				return "image/gif";
			}
		}

		return "image/jpeg"; // fallback
	}

}
