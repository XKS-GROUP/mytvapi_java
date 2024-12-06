package com.mytv.api.user.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mytv.api.dto.UserDTO;
import com.mytv.api.firebase.model.FirebaseUser;
import com.mytv.api.security.config.SecurityPrincipal;
import com.mytv.api.security.request.UserRegisterRequestDTO;
import com.mytv.api.user.model.Profil;
import com.mytv.api.user.model.Role;
import com.mytv.api.user.model.User;
import com.mytv.api.user.model.UserRole;
import com.mytv.api.user.repository.IUserRepository;
import com.mytv.api.user.repository.IUserRoleRepository;
import com.mytv.api.user.repository.ProfilRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class WUserService implements UserDetailsService {

	//private static final Logger LOG = LoggerFactory.getLogger(WUserService.class);

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IUserRoleRepository userRoleRepository;

	@Autowired
	private ValidationService validationService;
	
	@Autowired
	private ProfilRepository proRep;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	WRoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String email) {

		//User user = userRepository.findByEmail(email);
		
		User user = userRepository.findByEmailOrPhone(email, email);
		
		if (user != null) {
			List<UserRole> userRoles = userRoleRepository.findAllByUserId(user.getId());

			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

			userRoles.forEach(userRole -> {
				authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
			});

			UserDetails principal = new org.springframework.security.core.userdetails.User(user.getEmail(),
					user.getPassword(), authorities);

			return principal;
		}
		return null;
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByUserEmail(String email) {

		return userRepository.findByEmail(email);
	}
	
	public User findByUserEmailOrPhone(String email) {

		return userRepository.findByEmailOrPhone(email, email);
	}
	public User findByUserPhone(String email) {

		return userRepository.findByPhone(email);
	}
	
	public User updatePassword(Long id, String password) {

		User u = userRepository.findById(id).get();
		u.setPassword(password);
		
		userRepository.save(u);
		return u; //
	}
	
	public User findByIdAndPassword(Long id, String password) {
        
		//findById(id).getPassword();
		
		System.out.println( "le comparatif donne = " + passwordEncoder.matches("Ea.123@." , findById(id).getPassword()));
		
		
		return userRepository.findByIdAndPassword(id, passwordEncoder.encode(password));
		
	}
	
	
	//Creation d'un admin
	public String createUser(UserRegisterRequestDTO request) {
		try {
			User user = (User) dtoMapperRequestDtoToUser(request);
			
			user.setRole("ADMIN");
			user = userRepository.save(user);
			
			if (roleService.findRoleByName("ROLE_"+user.getRole()) == null ) {
				
				Role r = new Role();
				r.setName("ROLE_"+user.getRole());
				
				roleService.save(r);
				addUserRole(user, roleService.findRoleByName("ROLE_ADMIN"));
			}
			else {
				
				if (!request.getRoleList().isEmpty()) {
					
					for (String role : request.getRoleList()) {
						
						Role existingRole = roleService.findRoleByName("ROLE_" + role.toUpperCase());
						
						if(existingRole != null) {
							addUserRole(user, existingRole);
						}
					}
				} else {
					addUserRole(user, roleService.findRoleByName("ROLE_ADMIN"));
				}
			}
			//Envoi du mail pour la validation de son compte
			validationService.enregistrer(user);

			return "Un nouvel utilisateur , un mail a été envoyer a l adresse mail "+user.getEmail().toString();

		} catch (Exception e) {
			e.printStackTrace();
			return e.getCause().getMessage();
		}

	}
	
	//Activation d'un compte
    public void activation(Map<String, String> activation) {

        com.mytv.api.user.model.Validation validation = this.validationService.lireEnFonctionDuCode(activation.get("code"));

        if(Instant.now().isAfter(validation.getExpiration())){
            throw  new RuntimeException("Votre code a expiré");
        }
        User usr = userRepository.findById(validation.getUtilisateur().getId()).orElseThrow(() -> new RuntimeException("Cette utilisateur n existe pas"));
        usr.setValide(true);

        userRepository.save(usr);
    }

    //Liste des utilisateur active
	public List<User> AllUserValide(){

		return userRepository.findByValideTrue();
	}
	
	//Liste des Utilisateur non Active
	public List<User> AllUserNotValide(){

		return userRepository.findByValideFalse();
	}
	
	//Liste de tous les Utilisateurs
	public List<User> retrieveAllUserList() {
		return userRepository.findAll();
	}

	public Page<User> retrieveAllUserListPages(Pageable p) {
		return userRepository.findAll(p);
	}
	
	public User updateJWT(User user, String jwt) {

		User old = userRepository.findByEmail(user.getEmail());

		old.setRemember_token(jwt);

		user = userRepository.save(user);

		addUserRole(user, roleService.findRoleByName("ROLE_ADMIN"));

		return user;
	}
	
	public User updateInfo(UserDTO u, String Email) {
		
		 User OldUser = userRepository.findByEmail(Email);
		 OldUser.setUsername(u.getUsername());
		 OldUser.setEmail(u.getEmail());
		 OldUser.setImageUrl(u.getImageUrl());
		 OldUser.setPhone(u.getPhone());
		 
		 
		 return userRepository.save(OldUser);
	}
	
    public User updateByid(Long id, User u) {

		User old = userRepository.findById(id).get();
		
		old = u;
		old.setId(id);

		return userRepository.save(old);
	}
    
    
    public User updateByidUsrDTO(Long id, UserDTO u) {

		User old = userRepository.findById(id).get();
		old.setId(id);
		old.setEmail(u.getEmail());
		old.setImageUrl(u.getImageUrl());
		old.setValide(u.isValide());
		old.setUsername(u.getUsername());

		return userRepository.save(old);
	}
	
    public User updateUser(UserRegisterRequestDTO userRequestDTO) {

		User user = (User) dtoMapperRequestDtoToUser(userRequestDTO);

		user = userRepository.save(user);
		addUserRole(user, roleService.findRoleByName("ROLE_USER"));

		return user;
	}

	public User updateAbonne(UserRegisterRequestDTO userRequestDTO) {

		User user = (User) dtoMapperRequestDtoToUser(userRequestDTO);

		user = userRepository.save(user);
		addUserRole(user, roleService.findRoleByName("ROLE_USER"));

		return user;
	}
	
	//Renvoi  utilisateur actuellement connecte
	public User findCurrentUser() {
		
		System.out.println(" l actuel connecte est ici ");
		return userRepository.findById(SecurityPrincipal.getInstance().getLoggedInPrincipal().getId()).get();

	}
	
	//Supp l utilisateur actuel par id
	public String deleteUserFromId(Long id) {

		userRepository.deleteById(id);

		return "Utilisateur "+this.findCurrentUser().getUsername()+"a été supprimer avec succès";
	}
	
	//Supp l utilisateur actuel
	public String deleteCurrentUser() {

		userRepository.delete(this.findCurrentUser());

		return "Utilisateur "+this.findCurrentUser().getUsername()+"a été supprimer avec succès";
	}
	
	//Renvoi la liste des roles
	public List<UserRole> findAllCurrentUserRole() {
		return userRoleRepository.findAllByUserId(SecurityPrincipal.getInstance().getLoggedInPrincipal().getId());

	}

	public Optional<User> findUserById(long id) {
		return userRepository.findById(id);
	}
	
	
	//Ajouter un role a un utilisateur
	public void addUserRole(User user, Role role) {

		if (role == null) {
			role = roleService.findDefaultRole();
		}
		
		UserRole userRole = new UserRole();
		
		userRole.setUser(user);
		userRole.setRole(role);
		userRoleRepository.save(userRole);
	}
	
	
	//Hidradation DTO 
	private Object dtoMapperRequestDtoToUser(UserRegisterRequestDTO source) {
		User target = new User();
		target.setUsername(source.getUsername());
		target.setPassword(source.getPassword());
		target.setEmail(source.getEmail());
		target.setPhone(source.getPhone());
		
		return target;
	}


}
