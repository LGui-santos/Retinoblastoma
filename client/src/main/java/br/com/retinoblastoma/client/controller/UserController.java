package br.com.retinoblastoma.client.controller;

import java.util.ArrayList;
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
import br.com.retinoblastoma.client.service.impl.UserServiceImpl;
import br.com.retinoblastoma.model.dto.UserDto;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserAuthenticationProvider authProvider;

	@GetMapping("/my-profile")
	public String getMyProfilePage(Model model) {

		UserDto authenticatedUser = authProvider.getAuthenticatedUser();

		model.addAttribute("userDto", authenticatedUser);

		return "user/detail";
	}

	@GetMapping("/detail/{id}")
	public String getDetailPage(@PathVariable("id") Long id, Model model) {

		UserDto user = userService.readById(id);

		model.addAttribute("userDto", user);

		return "user/detail";
	}

	@GetMapping("/edit/{id}")
	public String getEditPage(@PathVariable("id") Long id, Model model) {

		UserDto UserDto = userService.readById(id);

		model.addAttribute("user", UserDto);

		return "user/edit";
	}

	@GetMapping("/list")
	public String getListPage(Model model) {

		List<UserDto> users = userService.readAll();

		if (users != null && users.size() != 0) {
			model.addAttribute("users", users);

			System.out.println("Foram encontrados " + users.size() + " UserDtos.");

		} else {
			model.addAttribute("users", new ArrayList<UserDto>());

			System.out.println("Nenhum UserDto foi encontrado");
		}

		return "user/list";
	}

//	@PostMapping("/update")
//	public String update(UserDto user, Model model) {
//
//		userService.update(user);
//
//		return getDetailPage(user.getId(), model);
//	}

	@PostMapping("/create")
	public String create(UserDto user, Model model) {

		Long id = userService.create(user);

		if (id == 0) {
			return "redirect:/account/register?serverError";
		}

		user.setId(id);

		model.addAttribute("user", user);

		return "home";
	}

//	@PostMapping("/upload-file")
//	public String uploadFile(Model model, @RequestParam("userId") Long userId, @RequestParam("file") MultipartFile file,
//			RedirectAttributes redirect) {
//
//		try {
//			if (file.isEmpty()) {
//				redirect.addFlashAttribute("message", "Por favor, escolha uma imagem e tente novamente.");
//				return "redirect:/user/edit/" + userId;
//			}
//
//			boolean response = userService.updateAvatar(userId, file);
//			if (!response) {
//				redirect.addFlashAttribute("message", "Erro ao fazer o upload da imagem. Tente novamente.");
//
//				return "redirect:/user/edit/" + userId;
//			}
//
//			return getEditPage(userId, model);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//
//			redirect.addFlashAttribute("message", "Erro ao fazer o upload da imagem. Tente novamente.");
//
//			return "redirect:/user/edit/" + userId;
//		}
//	}
}