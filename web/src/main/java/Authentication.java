import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.usecase.AuthenticateUserUsecase;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Authentication {


    public static String extractUserIdFromSession(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if(cookies==null || cookies.length==0 || !cookies[0].getName().equals("session"))
            throw new RuntimeException("user is not authenticated ");

        AuthenticateUserUsecase userUsecase = new AuthenticateUserUsecase();
        User user = userUsecase.decryptToken(cookies[0].getValue());

        return  user.getId();
    }

}
