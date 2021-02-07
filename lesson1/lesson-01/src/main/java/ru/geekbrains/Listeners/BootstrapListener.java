package ru.geekbrains.Listeners;

import ru.geekbrains.persist.Repository;
import ru.geekbrains.persist.User;
import ru.geekbrains.persist.UserRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Repository<User> userRepository = new UserRepository();

        userRepository.insert(new User("user1"));
        userRepository.insert(new User("user2"));
        userRepository.insert(new User("user3"));
        userRepository.insert(new User("user4"));

        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
