package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@Ignore
public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenAdd() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        cinema.add(new Session3D());
        assertThat(cinema.find(session -> true).size(), is(2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenBuyIncorrectDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2027, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenBuyIncorrectRow() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 150, 1, date);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenBuyIncorrectColumn() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1000, date);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenBuyIncorrectTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 10, 11, 23, 00);
        Ticket ticketOne = cinema.buy(account, 1, 1, date);
        Ticket ticketTwo = cinema.buy(account, 1, 1, date);
    }

}