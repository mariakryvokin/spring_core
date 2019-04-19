package ua.epam.spring.hometask.service.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import ua.epam.spring.hometask.dao.impl.JdbcUserDaoImpl;
import ua.epam.spring.hometask.entity.User;

import java.util.*;

import static org.junit.Assert.*;

/* We don’t want to call the real implementation of the CustomerDao save() method for a few reasons:

         We only want to test the logic inside the UserServiceImpl() in isolation.
         We may not yet have implemented it.
         We don’t want the unit test of the UserServiceImpl() fail if there is a defect in save() method in the jdbcUserDao.*/

public class UserServiceImplTest {

    //@Mock will create a mock implementation for the JdbcUserDaoImpl
    @Mock
    JdbcUserDaoImpl jdbcUserDao;

    //@InjectMocks will inject the mocks marked with @Mock to this instance when it is created.
    @InjectMocks
    UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    //So these instances would be created at the start of every test method of this test class.
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserByEmail() {
        User user = new User();
        user.setEmail("Email");
        Mockito.when(jdbcUserDao.getUserByEmail("Email")).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserByEmail("Email").get());
    }

    @Test
    public void getAllUserTickets() {
    }

    @Test
    public void save() {
        Mockito.when(jdbcUserDao.save(Mockito.any(User.class))).thenReturn(new User());
        User user = new User();
        assertThat(userService.save(user), CoreMatchers.is(CoreMatchers.notNullValue()));
    }

    //mock-объект будет запоминать любые вызовы его методов, чтобы после Вы могли проверить какие методы вызывал ваш тестируемый код у mock-объекта
    @Test
    public void remove() {
        userService.remove(new User());
        Mockito.verify(jdbcUserDao).remove(Mockito.any(User.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getById() {
        userService.getById(null);
    }

    @Test
    public void getAll() {
        List<User> users = new ArrayList<>();
        users.add( new User());
        Mockito.when(jdbcUserDao.getAll()).thenReturn(users);
        assertTrue(userService.getAll().size() != 0);
        assertTrue(userService.getAll().equals(users));
    }

    @Test
    public void getTicketsPrice() {
    }
}