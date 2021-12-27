package ru.fadedfog.bludbourne.profile;

import java.util.Enumeration;
import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

public class ProfileManager extends ProfileSubject {
	private Json json;
	private static ProfileManager profileManager;
	private Hashtable<String, FileHandle> profiles = null;
	private ObjectMap<String, Object> profileProperties = new ObjectMap<String, Object>();
	private String profileName;
	
	private static final String SAVEGAME_SUFFIX = ".sav";
	public static final String DEFAULT_PROFILE = "default";
	
	private ProfileManager() {
		json = new Json();
		profiles = new Hashtable<String, FileHandle>();
		profiles.clear();
		profileName = DEFAULT_PROFILE;
		storeAllProfiles();
	}
	
	public static final ProfileManager getInstance() {
		if (profileManager == null) {
			profileManager = new ProfileManager();
		}
		return profileManager;
	}
	
	public void storeAllProfiles() {
		if (Gdx.files.isLocalStorageAvailable()) {
			FileHandle[] files = Gdx.files.local(".").list(SAVEGAME_SUFFIX);
			for (FileHandle file: files) {
				profiles.put(file.nameWithoutExtension(), file);
			}
		} else {
			return;
		}
	}
	
	public Array<String> getProfileList() {
		Array<String> profilesKeys = new Array<String>();
		for (Enumeration<String> e = profiles.keys(); 
				e.hasMoreElements();) {
			profilesKeys.add(e.nextElement());
		}
		return profilesKeys;
	}
	
	public FileHandle getProfileFile(String profileKey) {
		if (!doesProfileExist(profileKey)) {
			return null;
		}
		return profiles.get(profileKey);
	}
	
	public boolean doesProfileExist(String profileKey) {
		return profiles.containsKey(profileKey);
	}
	
	public void writeProfileToStorage(String profileKey, String fileData, boolean overwrite) {
		String fullFilename = profileKey + SAVEGAME_SUFFIX;
		boolean localFileExists = Gdx.files.internal(fullFilename).exists();
		
		if (localFileExists && !overwrite) {
			return;
		}
		
		FileHandle file = null;
		if (Gdx.files.isLocalStorageAvailable()) {
			file = Gdx.files.local(fullFilename);
			file.writeString(fileData,  !overwrite);
		}
		
		profiles.put(profileName, file);
	}
	
	public void setProperty(String key, Object object) {
		profileProperties.put(key, object);
	}
	
	public <T extends Object> T getProperty(String key, Class<T> type) {
		T property = null;
		if (!profileProperties.containsKey(key)) {
			return property;
		}
		
		property = (T) profileProperties.get(key);
		return property;
	}
	
	public void saveProfile() {
		notify(this, ProfileObserver.ProfileEvent.SAVING_PROFILE);
		String text = json.prettyPrint(json.toJson(profileProperties));
		writeProfileToStorage(profileName, text, true);
	}
	
	public void loadProfile() {
		String fullProfileFileName = profileName + SAVEGAME_SUFFIX;
		boolean doesProfileFileExist = Gdx.files.internal(fullProfileFileName).exists();
		
		if (!doesProfileFileExist) {
			System.out.println("File doesn't exist!");
			return;
		}
		
		profileProperties = json.fromJson(ObjectMap.class, 
				profiles.get(profileName));
		notify(this, ProfileObserver.ProfileEvent.PROFILE_LOADED);
	}
	
	public void setCurrentProfile(String profileName) {
		if (doesProfileExist(profileName)) {
			this.profileName = profileName; 
		} else {
			this.profileName = profileName;
		}
	}
	
}
