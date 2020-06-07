package br.com.massariol.security.token;


import br.com.massariol.security.config.property.MassariolSecutiryProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

    private final MassariolSecutiryProperty massariolSecutiryProperty;

    public TokenResource(MassariolSecutiryProperty massariolSecutiryProperty) {
        this.massariolSecutiryProperty = massariolSecutiryProperty;
    }

    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(massariolSecutiryProperty.getSecurity().isEnableHttps());
        cookie.setPath(req.getContextPath() + massariolSecutiryProperty.getSecurity().getOauthPath());
        cookie.setMaxAge(0);

        resp.addCookie(cookie);
        resp.setStatus(HttpStatus.NO_CONTENT.value());
    }

}