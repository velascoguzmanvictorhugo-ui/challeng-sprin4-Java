package br.com.fiap.soulup.controller;

import br.com.fiap.soulup.dao.AccountDAO;
import br.com.fiap.soulup.dominio.Validador;
import br.com.fiap.soulup.service.PasswordHash;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {
    @Test void rejectsWrongPasswordAndDoesNotCreateSession() {
        var dao=mock(AccountDAO.class);
        when(dao.findByEmail("ana@example.test")).thenReturn(new AccountDAO.Credential(new Validador(1,"Ana","ana@example.test"),PasswordHash.create("correct-password")));
        var request=new MockHttpServletRequest();
        var result=new AuthController(dao).login(new AuthController.LoginRequest("ana@example.test","wrong-password"),request);
        assertEquals(401,result.getStatusCode().value()); assertNull(request.getSession(false));
    }
    @Test void acceptsCorrectPasswordAndRotatesSession() {
        var dao=mock(AccountDAO.class);
        when(dao.findByEmail("ana@example.test")).thenReturn(new AccountDAO.Credential(new Validador(1,"Ana","ana@example.test"),PasswordHash.create("correct-password")));
        var request=new MockHttpServletRequest(); String old=request.getSession().getId();
        var result=new AuthController(dao).login(new AuthController.LoginRequest("ANA@example.test","correct-password"),request);
        assertEquals(200,result.getStatusCode().value()); assertNotEquals(old,request.getSession().getId()); assertEquals(1,request.getSession().getAttribute("soulupUserId"));
    }
    @Test void rejectsAnonymousAndInvalidatesDeletedAccounts() {
        var dao=mock(AccountDAO.class); var controller=new AuthController(dao); var request=new MockHttpServletRequest();
        assertEquals(401,controller.current(request).getStatusCode().value());
        request.getSession().setAttribute("soulupUserId",1);
        assertEquals(401,controller.current(request).getStatusCode().value()); assertNull(request.getSession(false));
    }
    @Test void logoutInvalidatesSession() {
        var request=new MockHttpServletRequest(); request.getSession().setAttribute("soulupUserId",1);
        assertEquals(204,new AuthController(mock(AccountDAO.class)).logout(request).getStatusCode().value()); assertNull(request.getSession(false));
    }
    @Test void saltedHashesAreDistinctAndMalformedHashesFail() {
        String first=PasswordHash.create("correct-password"), second=PasswordHash.create("correct-password");
        assertNotEquals(first,second); assertTrue(PasswordHash.matches("correct-password",first)); assertFalse(PasswordHash.matches("incorrect",first)); assertFalse(PasswordHash.matches("correct-password","invalid"));
    }
}
