package br.org.fapes.analyzer.comum.util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.Cursor;

/**
 * Created on Nov 16, 2002 - 5:21:37 AM
 * @author rafael
*/
public class Splash extends Window 
{
	// Mensagem a ser mostrada junto com a imagem
	private final String message = "TRANSFTP";
	
	// Imagem que ira aparecer no Splash
        private final String imgName = "I:/Sistemas/FTP-IBM/splash.jpg";
        
	Image splashImage;
	Toolkit toolkit;
	private static Splash splash;
	
	// Singleton pra garantir apenas uma unica instancia na memoria
	static {
		splash = new Splash();
	}
	
	private Splash()
	{
		super(new Frame());
		//System.out.println("Criando Splash()...");
		setVisible(false);
		
		splashImage = null;
		toolkit = Toolkit.getDefaultToolkit();	
	}
	
	public static Splash getInstance()
	{
		return splash;
	}
	
	/**
	 * Method initSplash.
	 */
	private void initSplash() 
	{
		// Carrega a imagem na memoria
		MediaTracker media = new MediaTracker(this);
		splashImage = toolkit.getImage(imgName);
		
		if (splashImage != null) {
			media.addImage(splashImage, 0);
			
			try {
				media.waitForID(0);
			}
			catch (InterruptedException ie) {}
		}
		
		// Configura o tamanho do splash e a posicao na tela
		setSize(splashImage.getWidth(this), splashImage.getHeight(this));
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension size = getSize();
		
		if (size.width > screenSize.width)
			size.width = screenSize.width;
		
		if (size.height > screenSize.height)
			size.height = screenSize.height;
			
		setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
		setVisible(true);		
	}

	public void openSplash()
	{
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		initSplash();
	}
	
	public void finish()
	{
		setVisible(false);
		dispose();
	}
	
	public void paint(Graphics g)
	{
		// Apenas desenha a nossa mensagem em cima da imagem
		g.drawImage(splashImage, 0, 0, getBackground(), this);
		g.setFont(new Font("Arial", Font.BOLD, 32));
		g.drawString(message, (int)(splashImage.getWidth(this) / 2) - 80, 30);
	}

}
