package bookReviewer.presentation.security;

import bookReviewer.business.util.JwtProvider;
import bookReviewer.business.service.RatingService;
import bookReviewer.business.shared.model.Role;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.Map;

public class RoleMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    @Autowired
    RatingService ratingService = new RatingService();

    public RoleMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasRequiredRole(Map<String, String> headers, String minimumRequiredRole) {
        Claims decodedToken = getClaimsFromToken(headers);

        if(((long) (int) decodedToken.get("exp")) >= new Date().getTime()){
            System.out.println("Token expired");
            return false;
        }

        return checkRole(Role.valueOf(decodedToken.get("role").toString().toUpperCase()), Role.valueOf(minimumRequiredRole));
    }

    private Claims getClaimsFromToken(Map<String, String> headers) {
        String token = headers.get("authorization");
        String[] splittedToken = token.split(" ");
        return JwtProvider.decodeJWT(splittedToken[1]);
    }

    private boolean checkRole(Role role, Role minimumRequiredRole) {
        if (minimumRequiredRole == Role.USER) {
            return role == Role.USER || role == Role.MODERATOR || role == Role.ADMIN;
        }
        if (minimumRequiredRole == Role.MODERATOR) {
            return role == Role.ADMIN || role == Role.MODERATOR;
        }
        return role == Role.ADMIN;

    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }

}