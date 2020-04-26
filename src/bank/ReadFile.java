package bank;

import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileReader;
import java.util.*;

public class ReadFile {
    // class to read and parse the files of heroes, monsters and items

    public static Hero[] readHeroData(String filename, String type) {
        List<Hero> heroes = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int MP = Integer.parseInt(data[1]);
                    int strength = Integer.parseInt(data[2]);
                    int agility = Integer.parseInt(data[3]);
                    int dexterity = Integer.parseInt(data[4]);
                    int money = Integer.parseInt(data[5]);
                    int experience = Integer.parseInt(data[6]);
                    if (type.equals("W")) { Hero hero = new Warrior(name, 1, MP, strength, agility, dexterity, money, experience); heroes.add(hero); }
                    else if (type.equals("S")) { Hero hero = new Sorcerer(name, 1, MP, strength, agility, dexterity, money, experience); heroes.add(hero); }
                    else if (type.equals("P")) { Hero hero = new Paladin(name, 1, MP, strength, agility, dexterity, money, experience); heroes.add(hero); }
                    else { throw new IllegalArgumentException("Wrong type (W, S, or P are supported)"); }
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Hero[] res = new Hero[heroes.size()];
        res = heroes.toArray(res);
        return res;
    }

    public static Weapon[] readWeaponData(String filename) {
        List<Weapon> weapons = new ArrayList<Weapon>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/cost/level/damage/required hands
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int damage = Integer.parseInt(data[3]);
                    int hands = Integer.parseInt(data[4]);
                    Weapon weapon = new Weapon(name, cost, level, damage, hands);
                    weapons.add(weapon);
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Weapon[] res = new Weapon[weapons.size()];
        res = weapons.toArray(res);
        return res;
    }

    public static Armor[] readArmorData(String filename) {
        List<Armor> armors = new ArrayList<Armor>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/cost/required level/damage reduction
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int damageReduction = Integer.parseInt(data[3]);
                    Armor armor = new Armor(name, cost, damageReduction, level);
                    armors.add(armor);
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Armor[] res = new Armor[armors.size()];
        res = armors.toArray(res);
        return res;
    }

    public static Potion[] readPotionData(String filename) {
        List<Potion> potions = new ArrayList<Potion>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/cost/required level/attribute increase
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int attIncrease = Integer.parseInt(data[3]);
                    Potion potion = new Potion(name, cost, attIncrease, level);
                    potions.add(potion);
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Potion[] res = new Potion[potions.size()];
        res = potions.toArray(res);
        return res;
    }

    public static Monster[] readMonsterData(String filename, String type) {
        List<Monster> monsters = new ArrayList<Monster>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    // Name/level/damage/defense/dodge chance
                    int level = Integer.parseInt(data[1]);
                    int damage = Integer.parseInt(data[2]);
                    int defense = Integer.parseInt(data[3]);
                    double dodgeChance = (double)Integer.parseInt(data[4]);
                    dodgeChance = dodgeChance/100;
                    if (type.equals("D")) {Monster monster = new Dragon(name, level, damage, defense, dodgeChance);monsters.add(monster);}
                    else if (type.equals("S")) {Monster monster = new Spirit(name, level, damage, defense, dodgeChance);monsters.add(monster);}
                    else if (type.equals("E")) {Monster monster = new Exoskeleton(name, level, damage, defense, dodgeChance);monsters.add(monster);}
                    else { throw new IllegalArgumentException("Wrong type (D, S, or E are supported)"); }
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Monster[] res = new Monster[monsters.size()];
        res = monsters.toArray(res);
        return res;
    }

    public static Spell[] readSpellData(String filename, String type) {
        List<Spell> skills = new ArrayList<Spell>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    // Name/cost/required level/damage/mana cost
                    String name = data[0];
                    int cost = Integer.parseInt(data[1]);
                    int level = Integer.parseInt(data[2]);
                    int damage = Integer.parseInt(data[3]);
                    int manaCost = Integer.parseInt(data[4]);
                    if (type.equals("F")) {Spell skill = new FireSpell(name, cost, level, damage, manaCost);skills.add(skill);}
                    else if (type.equals("I")) {Spell skill = new IceSpell(name, cost, level, damage, manaCost);skills.add(skill);}
                    else if (type.equals("L")) {Spell skill = new LightingSpell(name, cost, level, damage, manaCost);skills.add(skill);}
                    else { throw new IllegalArgumentException("Wrong type (F, I, or L are supported)"); }
                }
                i ++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file "+filename);
            e.printStackTrace();
        }
        Spell[] res = new Spell[skills.size()];
        res = skills.toArray(res);
        return res;
    }

    public Dragon getRandomDragon()
    {

        String line = getRandomLineFromFile("Dragons.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Dragon(arguments.get(0),
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(2)),
                Double.parseDouble(arguments.get(4)));

    }

    public ArrayList<Dragon> getAllDragons()
    {
        ArrayList<Dragon> dragons = new ArrayList<>();

        try {
            File myObj = new File("Dragons.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int level = Integer.parseInt(data[1]);
                    int damage = Integer.parseInt(data[2]);
                    int defence = Integer.parseInt(data[3]);
                    int dodge = Integer.parseInt(data[4]);
                    Dragon dragon = new Dragon(name, level, damage, defence, dodge);
                    dragons.add(dragon);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file for dragons");
            e.printStackTrace();
        }
        return dragons;
    }

    public Exoskeleton getRandomExoskeleton()
    {

        String line = getRandomLineFromFile("Exoskeletons.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Exoskeleton(arguments.get(0),
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(2)),
                Double.parseDouble(arguments.get(4)));

    }

    public ArrayList<Exoskeleton> getAllExoskeletons()
    {
        ArrayList<Exoskeleton> exoskeletons = new ArrayList<>();

        try {
            File myObj = new File("Exoskeletons.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int level = Integer.parseInt(data[1]);
                    int damage = Integer.parseInt(data[2]);
                    int defence = Integer.parseInt(data[3]);
                    int dodge = Integer.parseInt(data[4]);
                    Exoskeleton exoskeleton = new Exoskeleton(name, level, damage, defence, dodge);
                    exoskeletons.add(exoskeleton);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file for Exoskeletons");
            e.printStackTrace();
        }
        return exoskeletons;
    }

    public Spirit getRandomSpirit()
    {

        String line = getRandomLineFromFile("Spirits.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }
        //System.out.println(arguments);


        return new Spirit(arguments.get(0),
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(2)),
                Double.parseDouble(arguments.get(4)));

    }

    public ArrayList<Spirit> getAllSpirits()
    {

        ArrayList<Spirit> spirits = new ArrayList<>();

        try {
            File myObj = new File("Spirits.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int level = Integer.parseInt(data[1]);
                    int damage = Integer.parseInt(data[2]);
                    int defence = Integer.parseInt(data[3]);
                    int dodge = Integer.parseInt(data[4]);
                    Spirit spirit = new Spirit(name, level, damage, defence, dodge);
                    spirits.add(spirit);
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file for spirits");
            e.printStackTrace();
        }
        return spirits;

    }

    public Weapon getRandomWeapon()
    {

        String line = getRandomLineFromFile("Weaponry.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Weapon(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                Integer.parseInt(arguments.get(2)),
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(3)));

    }

    public Armor getRandomArmor()
    {

        String line = getRandomLineFromFile("Armory.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Armor(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                Integer.parseInt(arguments.get(2)),
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(3)));

    }

    public Potion getRandomPotion()
    {

        String line = getRandomLineFromFile("Potions.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Potion(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                Integer.parseInt(arguments.get(2)),
                Integer.parseInt(arguments.get(1)),
                arguments.get(3),
                Double.parseDouble(arguments.get(4)));
    }

    public IceSpell getRandomIceSpell()
    {

        String line = getRandomLineFromFile("IceSpells.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new IceSpell(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                Integer.parseInt(arguments.get(2)),
                Double.parseDouble(arguments.get(1)),
                Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(4)));
    }

    public FireSpell getRandomFireSpell()
    {

        String line = getRandomLineFromFile("FireSpells.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new FireSpell(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                Integer.parseInt(arguments.get(2)),
                Double.parseDouble(arguments.get(1)),
                Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(4)));
    }

    public LightingSpell getRandomLightingSpell()
    {

        String line = getRandomLineFromFile("LightingSpells.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new LightingSpell(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                Integer.parseInt(arguments.get(2)),
                Double.parseDouble(arguments.get(1)),
                Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(4)));
    }

    public Warrior getRandomWarrior()
    {

        String line = getRandomLineFromFile("Warriors.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Warrior(
                arguments.get(0) + "/"+ arguments.get(1)+"$"+"/MinLevel:" + arguments.get(2),
                1,
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(2)),
                Double.parseDouble(arguments.get(4)),
                Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(5)),
                Double.parseDouble(arguments.get(6))

        );
    }

    public ArrayList<Warrior> getAllWarriors()
    {
        ArrayList<Warrior> warriors = new ArrayList<>();

        try {
            File myObj = new File("Warriors.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int MP = Integer.parseInt(data[1]);
                    int strength = Integer.parseInt(data[2]);
                    int agility = Integer.parseInt(data[3]);
                    int dexterity = Integer.parseInt(data[4]);
                    int money = Integer.parseInt(data[5]);
                    int experience = Integer.parseInt(data[6]);
                    Warrior warrior = new Warrior(name, 1, MP, strength, dexterity, agility, money, experience );
                    warriors.add(warrior);
                }
                i++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file for Exoskeletons");
            e.printStackTrace();
        }
        return warriors;
    }

    public Sorcerer getRandomSorcerer()
    {

        String line = getRandomLineFromFile("Sorcerers.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Sorcerer(
                arguments.get(0),
                1,
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(2)),
                Double.parseDouble(arguments.get(4)),
                Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(5)),
                Double.parseDouble(arguments.get(6))

        );
    }

    public ArrayList<Sorcerer> getAllSorcerers()
    {
        ArrayList<Sorcerer> sorcerers = new ArrayList<>();

        try {
            File myObj = new File("Sorcerers.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int MP = Integer.parseInt(data[1]);
                    int strength = Integer.parseInt(data[2]);
                    int agility = Integer.parseInt(data[3]);
                    int dexterity = Integer.parseInt(data[4]);
                    int money = Integer.parseInt(data[5]);
                    int experience = Integer.parseInt(data[6]);
                    Sorcerer sorcerer = new Sorcerer(name, 1, MP, strength, dexterity, agility, money, experience );
                    sorcerers.add(sorcerer);
                }
                i++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file for Exoskeletons");
            e.printStackTrace();
        }
        return sorcerers;
    }


    public Paladin getRandomPaladin()
    {

        String line = getRandomLineFromFile("Paladins.txt");
        String tokens[] = line.split("\\s");
        ArrayList<String> arguments = new ArrayList<>();
        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].equals(""))
            {
                arguments.add(tokens[i]);
            }
        }

        return new Paladin(
                arguments.get(0),
                1,
                Integer.parseInt(arguments.get(1)),
                Double.parseDouble(arguments.get(2)),
                Double.parseDouble(arguments.get(4)),
                Double.parseDouble(arguments.get(3)),
                Double.parseDouble(arguments.get(5)),
                Double.parseDouble(arguments.get(6))

        );
    }

    public ArrayList<Paladin> getAllPaladins()
    {
        ArrayList<Paladin> paladins = new ArrayList<>();

        try {
            File myObj = new File("Paladins.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                line = line.replaceAll("\\s+", " ");
                if (i != 0 && line.length() > 1) {
                    String[] data = line.split(" ");
                    String name = data[0];
                    int MP = Integer.parseInt(data[1]);
                    int strength = Integer.parseInt(data[2]);
                    int agility = Integer.parseInt(data[3]);
                    int dexterity = Integer.parseInt(data[4]);
                    int money = Integer.parseInt(data[5]);
                    int experience = Integer.parseInt(data[6]);
                    Paladin paladin = new Paladin(name, 1, MP, strength, dexterity, agility, money, experience );
                    paladins.add(paladin);
                }
                i++;
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred at parsing file for Exoskeletons");
            e.printStackTrace();
        }
        return paladins;
    }


    /**
     * finds a random line from the requested kind of object
     * @param s
     * @return
     */

    private String getRandomLineFromFile(String s)
    {
        //first find number of lines then pick a random one
        try{
            BufferedReader objReader = new BufferedReader(new FileReader(s));
            int numberOfLines = 0;
            while ((objReader.readLine()) != null) {
                numberOfLines++;
            }
            objReader.close();

            BufferedReader objReader2 = new BufferedReader(new FileReader(s));

            Random r = new Random();
            int randomLine = 0;
            while (randomLine == 0 )
            {
                randomLine = r.nextInt(numberOfLines -1);
            }

            String currentLine;
            int i = 0;
            while ((currentLine = objReader2.readLine()) != null) {
                if(i == randomLine)
                {
                    objReader2.close();
                    return currentLine;
                }
                i++;
            }
            System.out.println(i + " "+ randomLine);
            objReader2.close();

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;

    }

}