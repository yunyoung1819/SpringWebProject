package com.ese.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * [숙박 예약 컨트롤러]
 * 
 * @since : 2018.01.23
 * @author Yun Young
 *
 */
@Controller
public class BookingController {

	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

	@RequestMapping(value="/booking", method = RequestMethod.GET)
	public String bookingMap(Locale locale, Model model){
		
		logger.info("Welcome! booking Map Controller!");
		
		return "/booking/map";
	}
}
