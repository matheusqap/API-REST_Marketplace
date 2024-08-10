package com.github.qualquercoisavinteconto.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.qualquercoisavinteconto.enums.UserRoles;
import com.github.qualquercoisavinteconto.exceptions.UserAlreadyExistsException;
import com.github.qualquercoisavinteconto.mappers.UserMapper;
import com.github.qualquercoisavinteconto.models.Role;
import com.github.qualquercoisavinteconto.models.User;
import com.github.qualquercoisavinteconto.repositories.RoleRepository;
import com.github.qualquercoisavinteconto.repositories.UserRepository;
import com.github.qualquercoisavinteconto.requests.SigninRequest;
import com.github.qualquercoisavinteconto.requests.SignupRequest;
import com.github.qualquercoisavinteconto.responses.SigninResponse;
import com.github.qualquercoisavinteconto.responses.SignupResponse;
import com.github.qualquercoisavinteconto.services.AuthService;
import com.github.qualquercoisavinteconto.services.TokenService;

@Service
public class AuthServiceImpl implements AuthService {
        private final AuthenticationManager authManager;
        private final TokenService tokenService;
        private final UserRepository userRepository;
        private final RoleRepository roleRepository;

        public AuthServiceImpl(
                        AuthenticationManager authManager,
                        TokenService tokenService,
                        UserRepository userRepository,
                        RoleRepository roleRepository) {
                this.authManager = authManager;
                this.tokenService = tokenService;
                this.userRepository = userRepository;
                this.roleRepository = roleRepository;
        }

        @Override
        public SigninResponse signin(SigninRequest request) {
                var usernamePassword = new UsernamePasswordAuthenticationToken(request.getEmail(),
                                request.getPassword());

                var auth = this.authManager.authenticate(usernamePassword);

                var user = (User) auth.getPrincipal();

                var token = tokenService.createToken(user);

                var mappedUser = UserMapper.mapToIdentity(user);

                return SigninResponse.builder()
                                .accessToken(token)
                                .identity(mappedUser)
                                .build();
        }

        @Override
        public SignupResponse signup(SignupRequest request) throws UserAlreadyExistsException {
                Optional<User> user = this.userRepository.findByEmail(request.getEmail());

                if (user.isPresent())
                        throw new UserAlreadyExistsException();

                String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

                User newUser = new User(request.getName(), request.getEmail(), encryptedPassword);

                this.attachRolesByIds(newUser, List.of(UserRoles.CUSTOMER));

                this.userRepository.save(newUser);

                var token = tokenService.createToken(newUser);

                var mappedUser = UserMapper.mapToIdentity(newUser);

                var response = new SignupResponse();

                response.setAccessToken(token);
                
                response.setIdentity(mappedUser);

                return response;
        }

        public void attachRolesByIds(User user, List<UserRoles> roleIds) {
                List<Role> roles = new ArrayList<>();

                for (var roleId : roleIds) {
                        Role role = roleRepository.findById(roleId.getValue())
                                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleId));
                        roles.add(role);
                }

                user.setRoles(roles);
        }
}
