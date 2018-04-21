package games.epicduels.client.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import games.epicduels.message.data.Character;
import games.epicduels.message.data.Team;

public class GameDataUtil {
    
    private static Logger LOG = (Logger) LogManager.getLogger(GameDataUtil.class);

    public static List<Team> loadTeams() {
        
        List<Team> teams = new ArrayList<>();
        
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("Teams.properties")));
            for (String name: properties.get("teams").toString().split(",")) {
                teams.add(loadTeam(name, properties));
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        
        return teams;
    }
    
    private static Team loadTeam(String name, Properties properties) {
        
        Team team = new Team();
        List<Character> minorCharacters = new ArrayList<>();
        
        for (String charName: properties.get(name + ".minor").toString().split(",")) {
            minorCharacters.add(loadCharacter(charName, properties));
        }
        
        team.setName(properties.getProperty(name + ".name"));
        team.setMajorCharacter(loadCharacter(properties.get(name + ".major").toString(), properties));
        team.setMinorCharacters(minorCharacters);
        team.setImage("images/" + properties.getProperty(name + ".image"));
        
        return team;
    }
    
    private static Character loadCharacter(String name, Properties properties) {
        
        Character character = new Character();
        
        character.setName(properties.getProperty(name + ".name"));
        
        return character;
    }
    
    public static void main(String[] args) {
        
        List<Team> teams = loadTeams();

        teams.forEach(team -> {
            System.out.println(team.getName());
            
            Character majorCharacter = team.getMajorCharacter();
            System.out.println(majorCharacter.getName());
            
            team.getMinorCharacters().forEach(minorCharacter -> {
                System.out.println(minorCharacter.getName());
            });
        });
    }
}
