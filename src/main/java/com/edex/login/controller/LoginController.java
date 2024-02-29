package com.edex.login.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edex.login.model.Login;
import com.edex.login.repo.LoginRepo;

@RestController
@RequestMapping("/form")
public class LoginController {
	
	@Autowired
	private LoginRepo loginRepo;
	 @CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/signup")
	
	public ResponseEntity<?> signMapping(@RequestBody Login login){
		 try {
	            loginRepo.saveAndFlush(login);
	            return ResponseEntity.status(HttpStatus.OK)
	                                 .body("Registration Successful");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                                 .body("Error occurred during registration");
	        }
	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:3000")
	
	public ResponseEntity<?> loginMapping(@RequestBody Login login){
		
		System.out.println(login.getUsername());
		
		String a =login.getUsername();
		String b = login.getPassword();
				 
		Login log = loginRepo.findBy(a, b);
      
      return ResponseEntity.status(HttpStatus.OK)
      		.body(log);
		
	}
}
	



















//@GetMapping("/login")
//@CrossOrigin(origins = "http://localhost:3000")
//public ResponseEntity<?> loginMapping(@RequestParam String username, @RequestParam String password){
//   List<Login> login= loginRepo.findBy(username, password);
//   
//   return ResponseEntity.status(HttpStatus.OK)
//   		.body(login);
//}



//
// @GetMapping("/login")
//   @CrossOrigin(origins = "http://localhost:3000")
//   public ResponseEntity<?> loginMapping(@RequestParam String username, @RequestParam String password) {
//       Optional<Login> userOptional = loginRepo.findByUsername(username);
//       if (userOptional.isPresent()) {
//           Login user = userOptional.get();
//           if (user.getPassword().equals(password)) {
//               return ResponseEntity.status(HttpStatus.OK)
//                       .body(user);
//           }
//       }
//       return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//               .body("Invalid username or password");
//   }
//}
//

	
//	@PostMapping("/set")
//	@CrossOrigin(origins = "http://localhost:3000")
//	public ResponseEntity<?> signMapping(@RequestBody Login login){
//		Optional<Login> oLogin =loginRepo.find(login);
//      if(oLogin.isPresent()) {
//      	return ResponseEntity
//      			.status(HttpStatus.OK)
//      			.body(oLogin.get());
//      }
//      else {
//      	return ResponseEntity
//      			.status(HttpStatus.OK)
//      			.body(null);
//      }
//		
//	}
//}
	
	
	
	
//	@GetMapping("/set")
//
//	public ResponseEntity<?> setProduct(@RequestParam String username,@RequestParam String email,@RequestParam String password){
//		
//		Login login = new Login();
//
//		login.setUsername(username);
//		login.setEmail(email);
//		login.setPassword(password);
//		
//        Login savedEntity	= loginRepo.save(login);		
//
//	return ResponseEntity.status(HttpStatus.OK)
//			.body(savedEntity);
//	}

