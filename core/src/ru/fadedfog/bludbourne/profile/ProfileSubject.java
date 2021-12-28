package ru.fadedfog.bludbourne.profile;

import com.badlogic.gdx.utils.Array;

import ru.fadedfog.bludbourne.profile.ProfileObserver.ProfileEvent;

public class ProfileSubject {
	private Array<ProfileObserver> observers; 
	
	public ProfileSubject() {
		observers = new Array<ProfileObserver>();
	}
	
	public void addObserver(ProfileObserver profileObserver) {
		
	}
	
	public void removeObserver(ProfileObserver profileObserver) {
		
	}
	
	protected void notify(ProfileManager profileManager, ProfileEvent event) {
		
	}
	
}
