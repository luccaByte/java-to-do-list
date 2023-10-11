package br.com.luaccminerva.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.luaccminerva.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.var;

@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                // Pegar a autenticação (usuario e senha)
                var authorization = request.getHeader("Authorization");
                // remove a palavra basic e pega somente o auth, e após remove o espaço em branco com trim
                var authEncoded = authorization.substring("Basic".length()).trim(); 

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String (authDecode);

                // split cria um array colocando o usuario na posição 0, e a senha na posição 1
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
                System.out.println("Authorization");
                System.out.println(username);
                System.out.println(password);

                // Validar usuario
                var user = this.userRepository.findByUsername(username);
                if (user == null) {
                    response.sendError(401);
                } else {
                    // Validar senha
                    var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                    if (passwordVerify.verified) {
                        filterChain.doFilter(request, response);
                    } else {
                        response.sendError(401);
                    }

                    // Segue viagem
                }

    }

}