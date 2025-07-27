package com.cinema.app;

import com.cinema.controller.*;
import com.cinema.view.*;


public class AppContext {
    public static final CinemaController cinemaController = new CinemaController();
    public static final ClienteController clienteController = new ClienteController();
    public static AdminPage adminController;
}
