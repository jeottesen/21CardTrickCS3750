import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenAccessor;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Cubic;

public class Animations {
	
	// How many milliseconds we need to wait between frame updates
	

	public static void movePanel(JComponent component, int newX, int newY) {
			
			final TweenManager tweenManager = new TweenManager();
			
			// The thread that plays the animation
			Timer timer = new Timer(Globals.FRAME_RATE, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					// pass it how many milliseconds it has been since the
					// last frame update. This should always be about the same
					tweenManager.update(Globals.FRAME_RATE);
				}
			});
	
			Tween.registerAccessor(JComponent.class, new PanelAccessor());
			
			// Pass it the object to animate the direction to animate it and the
			// duration of the animation in milliseconds. when the time value is
			// 5000 it should take about 5 seconds to play the animation.
			Tween.to(component, PanelAccessor.POS_XY, 1050)
					// Start from the exact middle of the screen
					.target(newX, newY).ease(Cubic.OUT)
					// Stop the timer when the animation is complete
					.setCallbackTriggers(TweenCallback.COMPLETE)
					.setCallback(new TweenCallback() {
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							timer.stop();
						}
					})
					.start(tweenManager);
			
			timer.start();
			
	
		}


	/**
	 * Test and demonstration.
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame();
		frame.setLayout(null);
		Card card = new Card(CardValues.ACE, CardSuits.SPADES);
		Card card2 = new Card(CardValues.TWO, CardSuits.SPADES);
		card.loadImage();
		card2.loadImage();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		card.setLocation((Globals.FRAME_WI / 2) - (Globals.CARD_WI / 2), -1 * Globals.CARD_HI);
		frame.add(card);
		frame.setVisible(true);

		Animations.movePanel(card, Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY);
		
		card2.setLocation((Globals.FRAME_WI / 2) - (Globals.CARD_WI / 2), -1 * Globals.CARD_HI);
		frame.add(card2);
		
		Animations.movePanel(card2, Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY+ Globals.CARD_SPACING);

	}
}

// Used to tell Universal Tween Engine how to get and set the values of a JPanel
// Copied from the demo on the projects website and modified to work for JPanels
class PanelAccessor implements TweenAccessor<JComponent> {
	
	public static final int POS_XY = 1; // Position based on upper left corner of panel
	public static final int CPOS_XY = 2; // Position based on center of panel
	public static final int SCALE_XY = 3;
	public static final int ROTATION = 4;
	public static final int OPACITY = 5;
	public static final int TINT = 6;

	@Override
	public int getValues(JComponent target, int tweenType, float[] returnValues) {
		switch (tweenType) {
			case POS_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;

			case CPOS_XY:
				returnValues[0] = target.getX() + target.getWidth()/2;
				returnValues[1] = target.getY() + target.getHeight()/2;
				return 2;

			/*
			case SCALE_XY:
				returnValues[0] = target.getScaleX();
				returnValues[1] = target.getScaleY();
				return 2;

			case ROTATION: returnValues[0] = target.getRotation(); return 1;
			case OPACITY: returnValues[0] = target.getColor().a; return 1;

			case TINT:
				returnValues[0] = target.getColor().r;
				returnValues[1] = target.getColor().g;
				returnValues[2] = target.getColor().b;
				return 3;
			*/
			default: assert false; return -1;
		}
	}

	@Override
	public void setValues(JComponent target, int tweenType, float[] newValues) {
		switch (tweenType) {
			case POS_XY: target.setLocation((int) newValues[0], (int) newValues[1]); break;
			case CPOS_XY: target.setLocation((int) newValues[0] - target.getWidth()/2, (int) newValues[1] - target.getHeight()/2); break;
			/*
			case SCALE_XY: target.setScale(newValues[0], newValues[1]); break;
			case ROTATION: target.setRotation(newValues[0]); break;
			
			
			case OPACITY:
				Color c = target.getColor();
				c.set(c.r, c.g, c.b, newValues[0]);
				target.setColor(c);
				break;

			case TINT:
				c = target.getColor();
				c.set(newValues[0], newValues[1], newValues[2], c.a);
				target.setColor(c);
				break;
			*/

			default: assert false;
		}
	}

}