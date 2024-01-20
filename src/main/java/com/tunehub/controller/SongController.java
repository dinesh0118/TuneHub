package com.tunehub.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.tunehub.entities.Song;
import com.tunehub.services.SongService;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class SongController {

	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		
		boolean songStatus = service.songExists(song.getName());
		if(songStatus== false) {
			service.addSong(song);
			System.out.println("Song added Succesfully");
		}else {
			System.out.println("Song already Exsist");
		}
		
		return "adminHome";
	}
	@GetMapping("/playSongs")
	public String playSongs(Model model) {
		
		boolean premiumUser = false;
		
		if(premiumUser == true) {
			List<Song> songsList = service.fetchAllSongs();
			model.addAttribute("songs", songsList);
			return "displaySongs";
		}else {
			return "makePayment";
		}
		
	}
	
	
}
