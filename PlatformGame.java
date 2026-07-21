import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.*;
public class PlatformGame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Platform Game");
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.startGameThread();
    }
}

class GamePanel extends JPanel implements Runnable {
    final int SCREEN_WIDTH = 800;
    final int SCREEN_HEIGHT = 600;
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    Player player = new Player(100,100);
    int distanceScore = 0;
    int coinScore = 0;
    int score = 0;
    int highScore = 0;  
    ArrayList<Platform> platforms = new ArrayList<>();
    ArrayList<Coin> coins = new ArrayList<>();
    int cameraX = 0;

    public GamePanel(){
        setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        setBackground(new Color(135,206,235));
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(keyH);
        createWorld();
        loadHighScore();

    }

    public void createWorld(){

        // Ground //
        platforms.add(new Platform(0,550,100000,50));

        // Platforms //
        platforms.add(new Platform(250,450,150,20));
        platforms.add(new Platform(500,350,150,20));
        platforms.add(new Platform(750,250,150,20));
        platforms.add(new Platform(1050,400,200,20));
        platforms.add(new Platform(1350,300,180,20));
        platforms.add(new Platform(200,470,120,20));
        platforms.add(new Platform(380,400,120,20));
        platforms.add(new Platform(560,330,120,20));
        platforms.add(new Platform(760,260,120,20));
        platforms.add(new Platform(960,340,120,20));
        platforms.add(new Platform(1150,430,120,20));
        platforms.add(new Platform(1350,350,120,20));
        platforms.add(new Platform(1550,280,120,20));
        platforms.add(new Platform(1750,370,120,20));
        platforms.add(new Platform(1950,300,120,20));
        platforms.add(new Platform(2150,240,120,20));
        platforms.add(new Platform(2350,320,120,20));
        platforms.add(new Platform(2550,420,120,20));
        platforms.add(new Platform(2750,350,120,20));
        platforms.add(new Platform(2950,260,120,20));
        platforms.add(new Platform(3150,340,120,20));
        platforms.add(new Platform(3350,430,120,20));
        platforms.add(new Platform(3550,300,120,20));
        platforms.add(new Platform(3750,220,120,20));
        platforms.add(new Platform(3950,330,120,20));
        platforms.add(new Platform(4150,260,120,20));
        platforms.add(new Platform(4350,370,120,20));
        platforms.add(new Platform(4550,300,120,20));
        platforms.add(new Platform(4750,240,120,20));
        coins.add(new Coin(280,420));

        coins.add(new Coin(530,300));

        coins.add(new Coin(780,200));

        coins.add(new Coin(1080,370));

        coins.add(new Coin(1380,270));

        coins.add(new Coin(1780,340));

        coins.add(new Coin(2180,210));

        coins.add(new Coin(2580,390));

        coins.add(new Coin(3180,310));

        coins.add(new Coin(3780,180));
    }

    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }
    public void loadHighScore() {

    try {

        File file = new File("highscore.txt");

        if (!file.exists()) {

            file.createNewFile();
            return;

        }

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = br.readLine();

        if (line != null) {

            highScore = Integer.parseInt(line);

        }

        br.close();

    } catch (Exception e) {

        System.out.println("Unable to load high score.");

    }

}
    public void saveHighScore() {

    try {

        BufferedWriter bw = new BufferedWriter(new FileWriter("highscore.txt"));

        bw.write(String.valueOf(highScore));

        bw.close();

    } catch (Exception e) {

        System.out.println("Unable to save high score.");

    }

}
    @Override
    public void run(){
        while(gameThread!=null){
            player.update(keyH,platforms);
            for(Coin c : coins){
    if(!c.collected){
        Rectangle playerRect =
                new Rectangle(player.x,player.y,
                        player.width,player.height);
        Rectangle coinRect =
                new Rectangle(c.x,c.y,c.size,c.size);

        if(playerRect.intersects(coinRect)){
            c.collected = true;
            coinScore += 10;
            sound.play("coin.wav");
        }

    }

}
           distanceScore = Math.max(distanceScore, player.x / 10);
           score = distanceScore + coinScore; 
           if (score > highScore) {
           highScore = score;
           saveHighScore();

}
            cameraX=player.x-250;
            repaint();
            try{

                Thread.sleep(16);

            }

            catch(Exception e){

                e.printStackTrace();

            }

        }

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        g2.translate(-cameraX,0);
        // Sky //
        g2.setColor(new Color(135,206,235));
        g2.fillRect(cameraX,0,SCREEN_WIDTH,SCREEN_HEIGHT);
        // Platforms //
      
        for (Platform p : platforms) {
           p.draw(g2);
}

        // Coins //
        for (Coin c : coins) {
         c.draw(g2);
}

        // Player //
        player.draw(g2);

        // Camera back //
        g2.translate(cameraX,0);

        // Score //
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial",Font.BOLD,20));
        g2.drawString("Arrow Keys = Move",20,30);
        g2.drawString("Space = Jump",20,60);
        g2.drawString("Score : " + score,20,90);
        g2.drawString("High Score : " + highScore,20,120);
        if(player.gameOver){
            g2.setFont(new Font("Arial",Font.BOLD,50));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER",240,250);
            g2.setFont(new Font("Arial", Font.BOLD, 25));
            g2.setColor(Color.BLACK);
            g2.drawString("Final Score : " + score, 280, 300);
        }

    }

}
class Player {

    int x, y;
    int width = 40;
    int height = 50;
    double velocityX = 0;
    double velocityY = 0;
    
    // Normal values that we have in our Physics //
    final double gravity = 0.5;
    final double friction = 0.90;
    final double moveSpeed = 0.7;
    final double maxSpeed = 6;
    final double jumpForce = -15;
    boolean onGround = false;
    boolean gameOver = false;
    Sound sound = new Sound();
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void update(KeyHandler keyH, ArrayList<Platform> platforms) {
        if (gameOver)
            return;

        // For Moving Left //
        if (keyH.leftPressed) {
            velocityX -= moveSpeed;
        }

        //  For Moving Right //
        if (keyH.rightPressed) {
            velocityX += moveSpeed;
        }

        // Limit horizontal speed //
        if (velocityX > maxSpeed)
            velocityX = maxSpeed;

        if (velocityX < -maxSpeed)
            velocityX = -maxSpeed;

        // To Jump //
        if (keyH.jumpPressed && onGround) {
           velocityY = jumpForce;
           onGround = false;
           sound.play("jump.wav");
        }
        
        // Gravity //
        velocityY += gravity;

        // Friction //
        velocityX *= friction;

        // Stop tiny movement //
        if (Math.abs(velocityX) < 0.1)
            velocityX = 0;

        // Horizontal movement //
        x += (int) velocityX;

        // Horizontal Collision //
        for (Platform p : platforms) {

            if (Collision.check(this, p)) {

                if (velocityX > 0) {
                    x = p.x - width;
                }

                if (velocityX < 0) {
                    x = p.x + p.width;
                }

                velocityX = 0;
            }
        }

        // Vertical movement //
        y += (int) velocityY;
        onGround = false;

        // Vertical Collision //
        for (Platform p : platforms) {

            if (Collision.check(this, p)) {

                // Landing //
                if (velocityY > 0) {

                    y = p.y - height;

                    velocityY = 0;

                    onGround = true;
                }

                //  When Hitting bottom of the platform //
                else if (velocityY < 0) {

                    y = p.y + p.height;

                    velocityY = 0;
                }
            }
        }

        // Fall below world //
        if (y > 700 && !gameOver) {
        gameOver = true;
        sound.play("gameover.wav");

}
}

    public void draw(Graphics2D g2) {

        // Body //
        g2.setColor(Color.RED);
        g2.fillRect(x, y, width, height);

        // Eyes //
        g2.setColor(Color.WHITE);
        g2.fillOval(x + 8, y + 10, 8, 8);
        g2.fillOval(x + 24, y + 10, 8, 8);

        g2.setColor(Color.BLACK);
        g2.fillOval(x + 11, y + 13, 3, 3);
        g2.fillOval(x + 27, y + 13, 3, 3);

        // Legs //
        g2.drawLine(x + 10, y + height, x + 10, y + height + 8);
        g2.drawLine(x + 30, y + height, x + 30, y + height + 8);

    }
}

// PLATFORM CLASS //

class Platform {

    int x;
    int y;
    int width;
    int height;

    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void draw(Graphics2D g2) {
        g2.setColor(new Color(100, 70, 30));
        g2.fillRect(x, y, width, height);
        g2.setColor(Color.BLACK);
        g2.drawRect(x, y, width, height);

    }

}

// KEY HANDLER //

class KeyHandler implements KeyListener {

    boolean leftPressed;
    boolean rightPressed;
    boolean jumpPressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT)
            leftPressed = true;
        if (code == KeyEvent.VK_RIGHT)
            rightPressed = true;
        if (code == KeyEvent.VK_SPACE)
            jumpPressed = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT)
            leftPressed = false;

        if (code == KeyEvent.VK_RIGHT)
            rightPressed = false;

        if (code == KeyEvent.VK_SPACE)
            jumpPressed = false;

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
}

// COLLISION CLASS //

class Collision {
    public static boolean check(Player player, Platform platform) {
        return player.x < platform.x + platform.width &&
               player.x + player.width > platform.x &&
               player.y < platform.y + platform.height &&
               player.y + player.height > platform.y;

    }

}
class Coin {
    int x;
    int y;
    int size = 20;
    boolean collected = false;
    public Coin(int x,int y){
        this.x = x;
        this.y = y;
    }
    public void draw(Graphics2D g2){
        if(collected)
            return;

        g2.setColor(Color.YELLOW);
        g2.fillOval(x,y,size,size);
        g2.setColor(Color.ORANGE);
        g2.drawOval(x,y,size,size);

    }

}
class Sound {
    public void play(String soundFile) {
        try {
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(new File(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception e) {
            System.out.println("Couldn't play: " + soundFile);
        }
    }
}