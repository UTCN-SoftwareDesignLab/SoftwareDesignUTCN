package org.example.controller;

import org.example.model.security.User;
import org.example.model.validation.Notification;
import org.example.service.security.SecurityService;
import org.example.view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

  private final LoginView view;
  private final SecurityService securityService;


  public LoginController(LoginView view, SecurityService securityService) {
    this.view = view;
    this.securityService = securityService;
    this.view.setLoginButtonListener(new LoginButtonListener());
    this.view.setRegisterButtonListener(new RegisterButtonListener());
  }

  private class LoginButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String username = view.getUsername();
      String password = view.getPassword();

      Notification<User> res = securityService.login(username, password);
      if (!res.hasErrors()) {
        JOptionPane.showMessageDialog(view.getContentPane(), "Login successful!");
      } else {
        JOptionPane.showMessageDialog(view.getContentPane(), res.getFormattedErrors());
      }
    }
  }

  private class RegisterButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      String username = view.getUsername();
      String password = view.getPassword();

      Notification<User> registerNotification = securityService.register(username, password);

      if (registerNotification.hasErrors()) {
        JOptionPane.showMessageDialog(view.getContentPane(), registerNotification.getFormattedErrors());
      } else {
        JOptionPane.showMessageDialog(view.getContentPane(), "Registration successful!");
      }
    }
  }
}
