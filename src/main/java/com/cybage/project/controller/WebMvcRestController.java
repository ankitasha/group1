package com.cybage.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cybage.project.model.PojoToDtoConverter;
import com.cybage.project.model.Requirement;
import com.cybage.project.model.RequirementDTO;
import com.cybage.project.model.User;
import com.cybage.project.model.UserPojo;
import com.cybage.project.service.AttachmentService;
import com.cybage.project.service.RequirementElaborationService;
import com.cybage.project.service.RequirementLinkService;
import com.cybage.project.service.RequirementService;
import com.cybage.project.service.UserService;



@RestController
public class WebMvcRestController {

	@Autowired
	UserService userService;
	
	@Autowired
	RequirementService requirementService;
	@Autowired
	AttachmentService attachmentService;
	@Autowired
	RequirementElaborationService requirementElaborationService;
	@Autowired
	RequirementLinkService requirementLinkService;

		  
		@RequestMapping(value = "/user/", method = RequestMethod.GET)
		public ResponseEntity<User> userSecurity() {
	       User e = userService.findUserBySSO("mahesh");
	       return new ResponseEntity<User>(e, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/user1/", method = RequestMethod.GET)
		public ResponseEntity<User> userSecurity1() {
	       User e = userService.findUserBySSO("mahesh");
	       return new ResponseEntity<User>(e, HttpStatus.OK);
		}
	
	  
	  @RequestMapping(value = "/user/", method = RequestMethod.POST)
	    public ResponseEntity<Void> verifyUser(@RequestBody UserPojo user, UriComponentsBuilder ucBuilder) {
		  HttpHeaders headers = new HttpHeaders();
		  User test= userService.findUserBySSO(user.getUserName());
		  if(test==null)
		      return new ResponseEntity<Void>(headers, HttpStatus.BAD_REQUEST);
		  else	
	      return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	  }
	
	  @RequestMapping(value = "/requirements/", method = RequestMethod.POST)
		public ResponseEntity<Void> createRequirement(@RequestBody RequirementDTO requirement, UriComponentsBuilder ucBuilder) {
			System.out.println(" in Creating requirement " + requirement);
			int requirementId=requirementService.saveRequirement(requirement);
			
			System.out.println("==>Added Requirement with id "+requirementId);
			
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
	  @RequestMapping(value = "/updateRequirement/", method = RequestMethod.POST)
		public ResponseEntity<Void> updateRequirement(@RequestBody RequirementDTO requirement, UriComponentsBuilder ucBuilder) {
			System.out.println(" in updateRequirement " + requirement);
			/*int requirementId=requirementService.saveRequirement(requirement);
			
			System.out.println("==>Added Requirement with id "+requirementId);*/
			
			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}


		@RequestMapping(value = "/requirements/", method = RequestMethod.GET)
		public ResponseEntity<List<String>> listAllRequirements() {
			List<String> requirements = new ArrayList<String>();
			requirements.add("a");
			requirements.add("b");
			requirements.add("c");

			return new ResponseEntity<List<String>>(requirements, HttpStatus.OK);
		}

		@RequestMapping(value = "/deleteRequirements/", method = RequestMethod.POST)
		public ResponseEntity<Void> deleteRequirement(@RequestBody RequirementDTO requirement, UriComponentsBuilder ucBuilder) {
			System.out.println("deleting requirement " + requirement);

			HttpHeaders headers = new HttpHeaders();
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}

		@RequestMapping(value = "/viewAllRequirements/", method = RequestMethod.GET)
		public ResponseEntity<ArrayList<RequirementDTO>> listAllEmployees() {
			List<Requirement> requirements = requirementService.getAllRequirements();

			
			PojoToDtoConverter p=new PojoToDtoConverter();
			ArrayList<RequirementDTO> requirementDTO=new ArrayList<>();
			
			for (Requirement requirement : requirements) {
				requirementDTO.add(p.toRequirementDTO(requirement));
			
			}
			System.out.println("=======>>"+requirementDTO);




			return new ResponseEntity<ArrayList<RequirementDTO>>(requirementDTO, HttpStatus.OK);
		}
	  

}
