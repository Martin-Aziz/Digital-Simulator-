package SE1Projekt;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class GUI extends JFrame {

	private class ANDGatter implements Baustein {
		private int x, y, width, heigth, xAusgang, yAusgang, xEingang, anzEingaenge = 2;
		private Color color;
		private Gatter gatterType = Gatter.ANDGATTER;
		private Baustein ausgang;
		private Baustein[] eingaenge = new Baustein[anzEingaenge];
		private int anzVergebenerEingaenge = 0;

		ANDGatter(int x, int y) {
			this.x = x;
			this.y = y;
			this.width = 35;
			this.heigth = 50;
			this.color = color.LIGHT_GRAY;
			xAusgang = x + width;
			yAusgang = y + (int) (heigth * 0.45);
			xEingang = x - 4;
		}

		public int[] berechneYKoordinatenDerEingaenge() {
			int[] yEingaenge;

			yEingaenge = new int[anzEingaenge];
			int wert = (int) (y + 12.5);
			yEingaenge[0] = wert;

			for (int i = 1; i < yEingaenge.length; i++) {
				wert = wert + 25;
				yEingaenge[i] = wert;
			}
			yAusgang = y + (int) (heigth * 0.45);
			return yEingaenge;

		}

		public void aendereEingaenge(int anz) {
			if (anz > anzEingaenge) {
				heigth += 25 * Math.abs(anz - anzEingaenge);
				Baustein[] kopie = new Baustein[anz];
				for (int i = 0; i < eingaenge.length; i++) {
					kopie[i] = eingaenge[i];
				}
				eingaenge = kopie;
				anzEingaenge = anz;

				yAusgang = y + (int) (heigth * 0.45);
				repaint();
			}
		}

		@Override
		public boolean berechneErgebnis() {
			// TODO Auto-generated method stub
			boolean erg = true;
			for (int i = 0; i < eingaenge.length; i++) {
				if (eingaenge[i].berechneErgebnis() == false) {
					erg = false;
				}
			}
			return erg;
		}

		@Override
		public Gatter getType() {
			// TODO Auto-generated method stub
			return gatterType;
		}

		public int getXAusgang() {
			// TODO Auto-generated method stub
			return xAusgang;
		}

		public int getYAusgang() {
			// TODO Auto-generated method stub
			return yAusgang;
		}

		@Override
		public int getXEingang() {
			// TODO Auto-generated method stub
			return xEingang;
		}

		@Override
		public int getYEingang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setGatterEingang(Baustein eingang) {
			// TODO Auto-generated method stub
			eingaenge[anzVergebenerEingaenge] = eingang;
			anzVergebenerEingaenge++;

		}

		@Override
		public void setGatterAusgang(Baustein ausgang) {
			// TODO Auto-generated method stub
			this.ausgang = ausgang;

		}

	}

	private class ORGatter implements Baustein {
		private int x, y, width, heigth, xAusgang, yAusgang, xEingang, anzEingaenge = 2;
		private Color color;
		private Gatter gatterType = Gatter.ORGATTER;
		private Baustein ausgang;
		private Baustein[] eingaenge = new Baustein[anzEingaenge];
		private int anzVergebenerEingaenge = 0;

		ORGatter(int x, int y) {
			this.x = x;
			this.y = y;
			this.width = 35;
			this.heigth = 50;
			this.color = color.BLUE;
			xAusgang = x + width;
			yAusgang = y + (int) (heigth * 0.45);
			xEingang = x - 4;
		}

		public void aendereEingaenge(int anz) {
			heigth += 25 * Math.abs(anz - anzEingaenge);
			anzEingaenge = anz;
			Baustein[] kopie = new Baustein[anz];
			for (int i = 0; i < eingaenge.length; i++) {
				kopie[i] = eingaenge[i];
			}
			eingaenge = kopie;
			yAusgang = y + (int) (heigth * 0.45);
			repaint();
		}

		public int[] berechneYKoordinatenDerEingaenge() {
			int[] yEingaenge;

			yEingaenge = new int[anzEingaenge];
			int wert = (int) (y + 12.5);
			yEingaenge[0] = wert;

			for (int i = 1; i < yEingaenge.length; i++) {
				wert = wert + 25;
				yEingaenge[i] = wert;
			}
			return yEingaenge;

		}

		@Override
		public boolean berechneErgebnis() {
			// TODO Auto-generated method stub
			boolean erg = false;
			for (int i = 0; i < eingaenge.length; i++) {
				if (eingaenge[i].berechneErgebnis() == true) {
					erg = true;
				}
			}
			return erg;
		}

		@Override
		public Gatter getType() {
			// TODO Auto-generated method stub
			return gatterType;
		}

		public int getXAusgang() {
			// TODO Auto-generated method stub
			return xAusgang;
		}

		public int getYAusgang() {
			// TODO Auto-generated method stub
			return yAusgang;
		}

		@Override
		public int getXEingang() {
			// TODO Auto-generated method stub
			return xEingang;
		}

		@Override
		public int getYEingang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setGatterEingang(Baustein eingang) {
			// TODO Auto-generated method stub
			eingaenge[anzVergebenerEingaenge] = eingang;
			anzVergebenerEingaenge++;

		}

		@Override
		public void setGatterAusgang(Baustein ausgang) {
			// TODO Auto-generated method stub
			this.ausgang = ausgang;

		}

	}

	private class NOTGatter implements Baustein {
		private int x, y, width, heigth, xEingang, yEingang, xAusgang, yAusgang;
		Color color;
		private Gatter gatterType = Gatter.NOTGATTER;
		Baustein ausgang, eingang;

		NOTGatter(int x, int y) {
			this.x = x;
			this.y = y;
			this.width = 35;
			this.heigth = 35;
			this.color = color.MAGENTA;
			xEingang = x - 4;
			yEingang = y + 16;
			xAusgang = x + width;
			yAusgang = y + 16;
		}

		@Override
		public boolean berechneErgebnis() {
			// TODO Auto-generated method stub
			if (eingang.berechneErgebnis()) {
				return false;
			} else {
				return true;
			}
		}

		@Override
		public Gatter getType() {
			// TODO Auto-generated method stub
			return gatterType;
		}

		public int getXAusgang() {
			// TODO Auto-generated method stub
			return xAusgang;
		}

		public int getYAusgang() {
			// TODO Auto-generated method stub
			return yAusgang;
		}

		@Override
		public int getXEingang() {
			// TODO Auto-generated method stub
			return xEingang;
		}

		@Override
		public int getYEingang() {
			// TODO Auto-generated method stub
			return yEingang;
		}

		@Override
		public void setGatterEingang(Baustein eingang) {
			// TODO Auto-generated method stub
			this.eingang = eingang;
		}

		@Override
		public void setGatterAusgang(Baustein ausgang) {
			// TODO Auto-generated method stub
			this.ausgang = ausgang;

		}
	}

	private class Schalter implements Baustein {
		private int x, y, width, heigth, xAusgang, yAusgang;
		private Color color;
		private Gatter gatterType = Gatter.SCHALTER;
		private boolean status = false;
		private Baustein ausgang;

		Schalter(int x, int y) {
			this.x = x;
			this.y = y;
			width = 20;
			heigth = 20;
			xAusgang = x + 20;
			yAusgang = y + 8;
			color = Color.RED;
		}

		@Override
		public boolean berechneErgebnis() {
			// TODO Auto-generated method stub
			return status;
		}

		@Override
		public Gatter getType() {
			// TODO Auto-generated method stub
			return gatterType;
		}

		public int getXAusgang() {
			// TODO Auto-generated method stub
			return xAusgang;
		}

		public int getYAusgang() {
			// TODO Auto-generated method stub
			return yAusgang;
		}

		@Override
		public int getXEingang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getYEingang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setGatterEingang(Baustein eingang) {
			// TODO Auto-generated method stub
			return;
		}

		@Override
		public void setGatterAusgang(Baustein ausgang) {
			// TODO Auto-generated method stub
			this.ausgang = ausgang;

		}
	}

	private class Lampe implements Baustein {

		private int x, y, width, heigth, xEingang, yEingang;
		private Gatter gatterType = Gatter.LAMPE;
		private boolean status = false;
		private Color color;
		Baustein eingang;

		Lampe(int x, int y) {
			this.x = x;
			this.y = y;
			width = 16;
			heigth = 16;
			xEingang = x;
			yEingang = y + 6;
			color = color.DARK_GRAY;
		}

		@Override
		public boolean berechneErgebnis() {

			return eingang.berechneErgebnis();
		}

		@Override
		public Gatter getType() {
			// TODO Auto-generated method stub
			return gatterType;
		}

		@Override
		public int getXAusgang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getYAusgang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getXEingang() {
			// TODO Auto-generated method stub
			return xEingang;
		}

		@Override
		public int getYEingang() {
			// TODO Auto-generated method stub
			return yEingang;
		}

		@Override
		public void setGatterEingang(Baustein eingang) {
			// TODO Auto-generated method stub
			this.eingang = eingang;

		}

		@Override
		public void setGatterAusgang(Baustein ausgang) {
			// TODO Auto-generated method stub
			return;
		}

	}

	private class Leitung implements Baustein {

		private int xEingang = 0, yEingang = 0, xAusgang = 0, yAusgang = 0, xMitte = 0;
		private Gatter gatterType = Gatter.LEITUNG;
		private Baustein ausgang, eingang;
		private boolean isSelected = false;

		Leitung() {
		}

		@Override
		public boolean berechneErgebnis() {
			// TODO Auto-generated method stub
			return eingang.berechneErgebnis();
		}

		@Override
		public Gatter getType() {
			// TODO Auto-generated method stub
			return gatterType;
		}

		public int getXAusgang() {
			// TODO Auto-generated method stub
			return xAusgang;
		}

		public int getYAusgang() {
			// TODO Auto-generated method stub
			return yAusgang;
		}

		@Override
		public int getXEingang() {
			// TODO Auto-generated method stub
			return xEingang;
		}

		@Override
		public int getYEingang() {
			// TODO Auto-generated method stub
			return yEingang;
		}

		@Override
		public void setGatterEingang(Baustein eingang) {
			// TODO Auto-generated method stub
			return;

		}

		@Override
		public void setGatterAusgang(Baustein ausgang) {
			// TODO Auto-generated method stub
			return;
		}

	}

	private class PopUp extends JPopupMenu {
		int neueEingaenge;
		ORGatter currentOr;
		ANDGatter currentAnd;

		PopUp() {

			JTextField tfName = new JTextField("", 3);

			tfName.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					neueEingaenge = Integer.parseInt(tfName.getText());
					if (currentOr != null) {
						currentOr.aendereEingaenge(neueEingaenge);
					} else {
						currentAnd.aendereEingaenge(neueEingaenge);
					}

					tfName.setEnabled(false);

				}
			});

			add(tfName);

		}

		public int giveNeueEingaenge() {
			return neueEingaenge;
		}

		public void setCurrent(ORGatter or) {
			currentOr = or;

		}

		public void setCurrent(ANDGatter and) {
			currentAnd = and;

		}

	}

	private class MyCanvas extends JPanel implements MouseListener {

		private ArrayList<Baustein> bausteinList = new ArrayList<Baustein>();
		private ANDGatter andgatter;
		private ORGatter orgatter;
		private NOTGatter notgatter;
		private Schalter schalter;
		private Lampe lampe;
		private Leitung leitung;
		private Gatter aktuellesGatter;
		private Modus aktuellerModus = Modus.BAUMODUS;
		private Color color;
		private String filename = "";

		MyCanvas() {
			setPreferredSize(new Dimension(1000, 700));
			color = Color.BLUE;
			addMouseListener(this);
		}

		public void paint(Graphics g) {
			g.setColor(Color.RED);
			g.clearRect(0, 0, this.getSize().width, this.getSize().height);
			for (Baustein baustein : bausteinList) {

				if (baustein.getType() == Gatter.ANDGATTER) {
					ANDGatter and = (ANDGatter) baustein;
					g.setColor(and.color);
					g.fillRect(and.x, and.y, and.width, and.heigth);
					g.setColor(color.BLACK);
					Font myFont = new Font("Default", 1, 25);
					g.setFont(myFont);
					g.drawString("&", (and.x + and.width / 4), (and.y + (int) (and.heigth / 1.4)));
					g.setColor(color.BLACK);
					g.fillRect(and.xAusgang, and.yAusgang, 4, 4);
					for (int i = 0; i < and.berechneYKoordinatenDerEingaenge().length; i++) {
						g.fillRect(and.xEingang, and.berechneYKoordinatenDerEingaenge()[i], 4, 4);

					}

				} else if (baustein.getType() == Gatter.ORGATTER) {
					ORGatter or = (ORGatter) baustein;
					g.setColor(or.color);
					g.fillRect(or.x, or.y, or.width, or.heigth);
					g.setColor(color.BLACK);
					Font myFont = new Font("Default", 1, 15);
					g.setFont(myFont);
					g.drawString("<=1", (or.x + or.width / 6), (or.y + (int) (or.heigth / 1.6)));
					g.setColor(color.BLACK);
					g.fillRect(or.xAusgang, or.yAusgang, 4, 4);
					for (int i = 0; i < or.berechneYKoordinatenDerEingaenge().length; i++) {
						g.fillRect(or.xEingang, or.berechneYKoordinatenDerEingaenge()[i], 4, 4);

					}

				} else if (baustein.getType() == Gatter.NOTGATTER) {
					NOTGatter not = (NOTGatter) baustein;
					g.setColor(not.color);
					g.fillRect(not.x, not.y, not.width, not.heigth);
					g.setColor(color.BLACK);
					Font myFont = new Font("Times New Roman", 1, 20);
					g.setFont(myFont);
					g.drawString("not", (not.x + (not.width / 5) - 3), (not.y + (int) (not.heigth / 1.4)));
					g.setColor(color.BLACK);
					g.fillRect(not.xEingang, not.yEingang, 4, 4);
					g.fillRect(not.xAusgang, not.yAusgang, 4, 4);

				} else if (baustein.getType() == Gatter.SCHALTER) {
					Schalter schalter = (Schalter) baustein;
					g.setColor(schalter.color);
					g.fillRect(schalter.x, schalter.y, schalter.width, schalter.heigth);
					g.setColor(color.BLACK);
					g.fillRect(schalter.xAusgang, schalter.yAusgang, 4, 4);
				} else if (baustein.getType() == Gatter.LAMPE) {
					Lampe lampe = (Lampe) baustein;
					if (lampe.status == false) {
						g.setColor(lampe.color);
						g.fillOval(lampe.x, lampe.y, lampe.width, lampe.heigth);
						g.setColor(color.BLACK);
						g.fillRect(lampe.xEingang, lampe.yEingang, 4, 4);
					} else {
						g.setColor(color.YELLOW);
						g.fillOval(lampe.x, lampe.y, lampe.width, lampe.heigth);
					}

				} else {
					Leitung leitung = (Leitung) baustein;
					g.setColor(color.BLACK);

					g.drawLine(leitung.xEingang, leitung.yEingang, leitung.xMitte, leitung.yEingang);
					g.drawLine(leitung.xMitte, leitung.yEingang, leitung.xMitte, leitung.yAusgang);
					g.drawLine(leitung.xMitte, leitung.yAusgang, leitung.xAusgang, leitung.yAusgang);
				}

			}

		}

		public void mousePressed(final MouseEvent e) {
			if (aktuellerModus == Modus.BAUMODUS) {
				if (checkIfLeitung(e.getX(), e.getY())) {
					Leitung l = getLeitung(e.getX(), e.getY());
					l.isSelected = true;
				}

				if (aktuellesGatter == Gatter.LEITUNG) {
					boolean outputGefunden = checkIfOnOutput(e.getX(), e.getY());
					if (outputGefunden) {
						leitung = new Leitung();
						leitung.eingang = getOutput(e.getX(), e.getY());
						leitung.xEingang = getOutputX(e.getX(), e.getY());
						leitung.yEingang = getOutputY(e.getX(), e.getY());
						Baustein x = getOutput(e.getX(), e.getY());
						x.setGatterAusgang(leitung);
					}

				}
			}

		}

		public void mouseReleased(MouseEvent e) {
			if (aktuellerModus == Modus.BAUMODUS) {

				for (Baustein b : bausteinList) {
					if (b.getType() == Gatter.LEITUNG) {
						Leitung l = (Leitung) b;
						if (l.isSelected) {
							l.xMitte = e.getX();
							repaint();
							l.isSelected = false;
						}

					}
				}

				if (aktuellesGatter == Gatter.LEITUNG) {
					if (leitung != null) {
						if (checkIfOnInput(e.getX(), e.getY())) {
							leitung.xAusgang = e.getX();
							leitung.yAusgang = e.getY();
							leitung.xMitte = (leitung.xEingang + leitung.xAusgang) / 2;
							Baustein b = getInput(e.getX(), e.getY());
							leitung.ausgang = b;
							b.setGatterEingang(leitung);
							bausteinList.add(leitung);
							leitung = null;
							repaint();
						} else
							leitung = null;

					}

				}
			}
		}

		public void removeLastGatter() {
			if (aktuellerModus == Modus.BAUMODUS) {
				if (bausteinList.get(bausteinList.size() - 1).getType() == Gatter.LEITUNG) {
					Leitung leitung = (Leitung) bausteinList.get(bausteinList.size() - 1);
					if (leitung.ausgang.getType() == Gatter.ANDGATTER) {
						ANDGatter and = (ANDGatter) leitung.ausgang;
						and.anzVergebenerEingaenge -= 1;

					} else if (leitung.ausgang.getType() == Gatter.ORGATTER) {
						ORGatter or = (ORGatter) leitung.ausgang;
						or.anzVergebenerEingaenge -= 1;
					}

				}
				bausteinList.remove(bausteinList.get(bausteinList.size() - 1));
				repaint();
				return;

			}
		}

		public boolean checkIfOnOutput(int x, int y) {
			int x1 = x - 5, x2 = x + 5, y1 = y - 5, y2 = y + 5;
			for (Baustein b : bausteinList) {
				if (b.getXAusgang() > x1 && b.getXAusgang() < x2 && b.getYAusgang() > y1 && b.getYAusgang() < y2) {

					return true;
				}
			}
			return false;

		}

		public Baustein getOutput(int x, int y) {
			int x1 = x - 5, x2 = x + 5, y1 = y - 5, y2 = y + 5;
			for (Baustein b : bausteinList) {
				if (b.getXAusgang() > x1 && b.getXAusgang() < x2 && b.getYAusgang() > y1 && b.getYAusgang() < y2) {

					return b;
				}

			}
			return null;
		}

		public Baustein getInput(int x, int y) {
			int x1 = x - 5, x2 = x + 5, y1 = y - 5, y2 = y + 5;
			int[] yWerte;
			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.ANDGATTER) {
					ANDGatter a = (ANDGatter) b;
					yWerte = a.berechneYKoordinatenDerEingaenge();
					if (a.xEingang > x1 && a.xEingang < x2) {
						for (int i = 0; i < yWerte.length; i++) {
							if (yWerte[i] > y1 && yWerte[i] < y2) {
								return b;
							}
						}
					}
				} else if (b.getType() == Gatter.ORGATTER) {
					ORGatter o = (ORGatter) b;
					yWerte = o.berechneYKoordinatenDerEingaenge();
					if (o.xEingang > x1 && o.xEingang < x2) {
						for (int i = 0; i < yWerte.length; i++) {
							if (yWerte[i] > y1 && yWerte[i] < y2) {
								return b;
							}
						}
					}
				} else {
					if (b.getXEingang() > x1 && b.getXEingang() < x2 && b.getYEingang() > y1 && b.getYEingang() < y2) {
						return b;
					}

				}

			}
			return null;
		}

		public boolean checkIfOnInput(int x, int y) {
			int x1 = x - 5, x2 = x + 5, y1 = y - 5, y2 = y + 5;
			int[] yWerte;
			boolean gefunden = false;
			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.ANDGATTER) {
					ANDGatter a = (ANDGatter) b;
					yWerte = a.berechneYKoordinatenDerEingaenge();
					if (a.xEingang > x1 && a.xEingang < x2) {
						for (int i = 0; i < yWerte.length; i++) {
							if (yWerte[i] > y1 && yWerte[i] < y2) {
								gefunden = true;
							}
						}
					}
				} else if (b.getType() == Gatter.ORGATTER) {
					ORGatter o = (ORGatter) b;
					yWerte = o.berechneYKoordinatenDerEingaenge();
					if (o.xEingang > x1 && o.xEingang < x2) {
						for (int i = 0; i < yWerte.length; i++) {
							if (yWerte[i] > y1 && yWerte[i] < y2) {
								gefunden = true;
							}
						}
					}
				} else {
					if (b.getXEingang() > x1 && b.getXEingang() < x2 && b.getYEingang() > y1 && b.getYEingang() < y2) {
						gefunden = true;
					}

				}

			}
			return gefunden;

		}

		public int getOutputX(int x, int y) {
			int x1 = x - 5, x2 = x + 5, y1 = y - 5, y2 = y + 5;
			for (Baustein b : bausteinList) {
				if (b.getXAusgang() > x1 && b.getXAusgang() < x2 && b.getYAusgang() > y1 && b.getYAusgang() < y2) {
					return b.getXAusgang();
				}
			}
			return 0;
		}

		public int getOutputY(int x, int y) {
			int x1 = x - 5, x2 = x + 5, y1 = y - 5, y2 = y + 5;
			for (Baustein b : bausteinList) {
				if (b.getXAusgang() > x1 && b.getXAusgang() < x2 && b.getYAusgang() > y1 && b.getYAusgang() < y2) {
					return b.getYAusgang();
				}
			}
			return 0;
		}

		public boolean checkIfOrGatter(int x, int y) {
			int x1, x2, y1, y2;

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.ORGATTER) {
					ORGatter o = (ORGatter) b;
					x1 = o.x;
					x2 = o.x + o.width;
					y1 = o.y;
					y2 = o.y + o.heigth;

					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						return true;
					}
				}
			}
			return false;
		}

		public boolean checkIfAndGatter(int x, int y) {
			int x1, x2, y1, y2;

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.ANDGATTER) {
					ANDGatter a = (ANDGatter) b;
					x1 = a.x;
					x2 = a.x + a.width;
					y1 = a.y;
					y2 = a.y + a.heigth;

					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						return true;
					}
				}
			}
			return false;
		}

		public boolean checkIfSchalter(int x, int y) {
			int x1, x2, y1, y2;

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.SCHALTER) {
					Schalter s = (Schalter) b;
					x1 = s.x;
					x2 = s.x + s.width;
					y1 = s.y;
					y2 = s.y + s.heigth;

					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						return true;
					}
				}
			}
			return false;
		}

		public boolean checkIfLeitung(int x, int y) {

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.LEITUNG) {
					Leitung l = (Leitung) b;

					if (x >= l.xMitte - 5 && x <= l.xMitte + 5
							&& (y >= l.yEingang && y <= l.yAusgang || y <= l.yEingang && y >= l.yAusgang)) {
						return true;
					}
				}
			}
			return false;
		}

		public Leitung getLeitung(int x, int y) {

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.LEITUNG) {
					Leitung l = (Leitung) b;

					if (x >= l.xMitte - 5 && x <= l.xMitte + 5
							&& (y >= l.yEingang && y <= l.yAusgang || y <= l.yEingang && y >= l.yAusgang)) {
						return l;
					}
				}
			}
			return null;
		}

		public Schalter getSchalter(int x, int y) {
			int x1, x2, y1, y2;

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.SCHALTER) {
					Schalter s = (Schalter) b;
					x1 = s.x;
					x2 = s.x + s.width;
					y1 = s.y;
					y2 = s.y + s.heigth;

					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						return s;
					}
				}
			}
			return null;
		}

		public ORGatter getOrGatter(int x, int y) {
			int x1, x2, y1, y2;

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.ORGATTER) {
					ORGatter o = (ORGatter) b;
					x1 = o.x;
					x2 = o.x + o.width;
					y1 = o.y;
					y2 = o.y + o.heigth;

					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						return o;
					}
				}
			}
			return null;

		}

		public ANDGatter getANDGatter(int x, int y) {
			int x1, x2, y1, y2;

			for (Baustein b : bausteinList) {
				if (b.getType() == Gatter.ANDGATTER) {
					ANDGatter a = (ANDGatter) b;
					x1 = a.x;
					x2 = a.x + a.width;
					y1 = a.y;
					y2 = a.y + a.heigth;

					if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
						return a;
					}
				}
			}
			return null;

		}

		public void mouseClicked(MouseEvent e) {

			if (aktuellerModus == Modus.BAUMODUS) {
				if (SwingUtilities.isRightMouseButton(e)) {

					if (checkIfOrGatter(e.getX(), e.getY())) {
						PopUp popUp = new PopUp();
						popUp.setCurrent(getOrGatter(e.getX(), e.getY()));
						popUp.show(e.getComponent(), e.getX(), e.getY());
					}
					if (checkIfAndGatter(e.getX(), e.getY())) {
						PopUp popUp2 = new PopUp();
						popUp2.setCurrent(getANDGatter(e.getX(), e.getY()));
						popUp2.show(e.getComponent(), e.getX(), e.getY());
					}
				} else if (SwingUtilities.isLeftMouseButton(e)) {

					if (aktuellesGatter == Gatter.ANDGATTER) {
						andgatter = new ANDGatter(e.getX(), e.getY());
						bausteinList.add(andgatter);
						repaint();
					} else if (aktuellesGatter == Gatter.ORGATTER) {
						orgatter = new ORGatter(e.getX(), e.getY());
						bausteinList.add(orgatter);
						repaint();
					} else if (aktuellesGatter == Gatter.NOTGATTER) {
						notgatter = new NOTGatter(e.getX(), e.getY());
						bausteinList.add(notgatter);
						repaint();
					} else if (aktuellesGatter == Gatter.SCHALTER) {
						schalter = new Schalter(e.getX(), e.getY());
						bausteinList.add(schalter);
						repaint();
					} else if (aktuellesGatter == Gatter.LAMPE) {
						lampe = new Lampe(e.getX(), e.getY());
						bausteinList.add(lampe);
						repaint();
					}
				}

			} else if (aktuellerModus == Modus.SIMULATIONSMODUS) {
				if (checkIfSchalter(e.getX(), e.getY())) {
					Schalter s = getSchalter(e.getX(), e.getY());
					if (s.status == false) {
						s.color = color.GREEN;
						s.status = true;
						repaint();
					} else {
						s.color = color.RED;
						s.status = false;
						repaint();

					}

				}

			}
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void run() {
			if (aktuellerModus == Modus.SIMULATIONSMODUS) {
				for (Baustein c : bausteinList) {
					if (c.getType() == Gatter.LAMPE) {
						Lampe l = (Lampe) c;
						if (l.berechneErgebnis()) {
							l.color = color.YELLOW;
							repaint();
						} else {
							l.color = color.DARK_GRAY;
							repaint();
						}
					}
				}
			}

		}

		public void safeFile() throws IOException {
			if (!filename.equals("")) {
				FileWriter filewriter = new FileWriter(filename, false);
				int pos = 0;
				for (Baustein baustein : bausteinList) {
					pos += 1;
					String type = baustein.getType().toString();
					if (baustein.getType() == Gatter.ANDGATTER) {
						ANDGatter and = (ANDGatter) baustein;

						filewriter.write(type + " " + and.x + " " + and.y);
					} else if (baustein.getType() == Gatter.ORGATTER) {
						ORGatter or = (ORGatter) baustein;

						filewriter.write(type + " " + or.x + " " + or.y);
					} else if (baustein.getType() == Gatter.NOTGATTER) {
						NOTGatter not = (NOTGatter) baustein;

						filewriter.write(type + " " + not.x + " " + not.y);
					} else if (baustein.getType() == Gatter.SCHALTER) {
						Schalter schalter = (Schalter) baustein;

						filewriter.write(type + " " + schalter.x + " " + schalter.y);
					} else if (baustein.getType() == Gatter.LAMPE) {
						Lampe lampe = (Lampe) baustein;

						filewriter.write(type + " " + lampe.x + " " + lampe.y);
					} else if (baustein.getType() == Gatter.LEITUNG) {
						Leitung leitung = (Leitung) baustein;

						filewriter.write(type + " " + leitung.xEingang + " " + leitung.yEingang + " " + leitung.xAusgang
								+ " " + leitung.yAusgang + " " + leitung.xMitte);
					}
					if (bausteinList.size() != pos)
						filewriter.write("\n");

				}
				filewriter.close();
			}

		}

		public void safeToFile(String name) throws IOException {
			filename = name;
			FileWriter filewriter = new FileWriter(name);
			int pos = 0;
			for (Baustein baustein : bausteinList) {
				pos += 1;
				String type = baustein.getType().toString();
				if (baustein.getType() == Gatter.ANDGATTER) {
					ANDGatter and = (ANDGatter) baustein;

					filewriter.write(type + " " + and.x + " " + and.y);
				} else if (baustein.getType() == Gatter.ORGATTER) {
					ORGatter or = (ORGatter) baustein;

					filewriter.write(type + " " + or.x + " " + or.y);
				} else if (baustein.getType() == Gatter.NOTGATTER) {
					NOTGatter not = (NOTGatter) baustein;

					filewriter.write(type + " " + not.x + " " + not.y);
				} else if (baustein.getType() == Gatter.SCHALTER) {
					Schalter schalter = (Schalter) baustein;

					filewriter.write(type + " " + schalter.x + " " + schalter.y);
				} else if (baustein.getType() == Gatter.LAMPE) {
					Lampe lampe = (Lampe) baustein;

					filewriter.write(type + " " + lampe.x + " " + lampe.y);
				} else if (baustein.getType() == Gatter.LEITUNG) {
					Leitung leitung = (Leitung) baustein;

					filewriter.write(type + " " + leitung.xEingang + " " + leitung.yEingang + " " + leitung.xAusgang
							+ " " + leitung.yAusgang + " " + leitung.xMitte);
				}
				if (bausteinList.size() != pos)
					filewriter.write("\n");

			}
			filewriter.close();
		}

		public void loadFromFile(File file) throws FileNotFoundException {
			bausteinList.clear();
			filename = file.getName();
			Scanner scanner = new Scanner(file);
			String type = "";
			int x, y, xEingang, yEingang, xAusgang, yAusgang, xMitte;
			while (scanner.hasNextLine()) {
				type = scanner.next();
				if (type.equals("ANDGATTER")) {
					x = scanner.nextInt();
					y = scanner.nextInt();
					bausteinList.add(new ANDGatter(x, y));
				} else if (type.equals("ORGATTER")) {
					x = scanner.nextInt();
					y = scanner.nextInt();
					bausteinList.add(new ORGatter(x, y));
				} else if (type.equals("NOTGATTER")) {
					x = scanner.nextInt();
					y = scanner.nextInt();
					bausteinList.add(new NOTGatter(x, y));
				} else if (type.equals("SCHALTER")) {
					x = scanner.nextInt();
					y = scanner.nextInt();
					bausteinList.add(new Schalter(x, y));
				} else if (type.equals("LAMPE")) {
					x = scanner.nextInt();
					y = scanner.nextInt();
					bausteinList.add(new Lampe(x, y));
				} else if (type.equals("LEITUNG")) {
					xEingang = scanner.nextInt();
					yEingang = scanner.nextInt();
					xAusgang = scanner.nextInt();
					yAusgang = scanner.nextInt();
					xMitte = scanner.nextInt();

					Leitung leitung = new Leitung();

					leitung.xEingang = xEingang;
					leitung.yEingang = yEingang;
					leitung.xAusgang = xAusgang;
					leitung.yAusgang = yAusgang;
					leitung.xMitte = xMitte;

					Baustein inputGatter = getOutput(xEingang, yEingang);
					leitung.eingang = inputGatter;
					inputGatter.setGatterAusgang(leitung);
					Baustein outputGatter = getInput(xAusgang, yAusgang);
					leitung.ausgang = outputGatter;
					outputGatter.setGatterEingang(leitung);
					bausteinList.add(leitung);
				}

			}
			repaint();

		}

		public void clear() {
			bausteinList.clear();
			repaint();
		}

	}

	GUI() {
		super("Digitalsimulator");
		MyCanvas canvas = new MyCanvas();
		add(canvas, BorderLayout.CENTER);

		JDialog meinJDialog = new JDialog();
		meinJDialog.setTitle("Digitalsimulator");
		meinJDialog.setSize(1500, 1200);

		Border bo = new LineBorder(Color.BLACK);

		JMenuBar Menuleiste = new JMenuBar();
		Menuleiste.setBorder(bo);

		JMenu menuFile = new JMenu("File");
		Menuleiste.add(menuFile);
		JMenuItem menuItemFileNew = new JMenuItem("Neu");
		JMenuItem menuItemFileOpen = new JMenuItem("Öffnen");
		JMenuItem menuItemFileSave = new JMenuItem("Speichern");
		JMenuItem menuItemFileSaveAs = new JMenuItem("Speichern als");
		JMenuItem menuItemFileExit = new JMenuItem("Beenden");

		menuFile.add(menuItemFileNew);
		menuItemFileNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.bausteinList.clear();
				canvas.filename = "";
				repaint();
			}
		});
		menuFile.add(menuItemFileOpen);

		menuItemFileOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(canvas) == fileChooser.APPROVE_OPTION) {
					try {
						canvas.loadFromFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		menuFile.add(menuItemFileSave);
		menuItemFileSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					canvas.safeFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		menuFile.add(menuItemFileSaveAs);
		menuItemFileSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showSaveDialog(canvas) == fileChooser.APPROVE_OPTION) {
					try {
						canvas.safeToFile(fileChooser.getSelectedFile().getName());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		menuFile.addSeparator();
		menuFile.add(menuItemFileExit);
		menuItemFileExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		// Bausteinauswahl
		JMenu menuBausteine = new JMenu("Bausteine");
		Menuleiste.add(menuBausteine);
		JMenuItem menuItemANDGatter = new JMenuItem("AND-Gatter");
		JMenuItem menuItemORGatter = new JMenuItem("OR-Gatter");
		JMenuItem menuItemNOTGatter = new JMenuItem("NOT-Gatter");
		JMenuItem menuItemSchalter = new JMenuItem("Schalter");
		JMenuItem menuItemLampe = new JMenuItem("Lampe");
		JMenuItem menuItemLeitung = new JMenuItem("Leitung");

		menuBausteine.add(menuItemANDGatter);
		menuBausteine.add(menuItemORGatter);
		menuBausteine.add(menuItemNOTGatter);
		menuBausteine.add(menuItemSchalter);
		menuBausteine.add(menuItemLampe);
		menuBausteine.add(menuItemLeitung);

		menuItemANDGatter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				canvas.aktuellesGatter = Gatter.ANDGATTER;
			}
		});
		menuItemORGatter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellesGatter = Gatter.ORGATTER;
			}
		});
		menuItemNOTGatter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellesGatter = Gatter.NOTGATTER;
			}
		});
		menuItemSchalter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellesGatter = Gatter.SCHALTER;
			}
		});
		menuItemLampe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellesGatter = Gatter.LAMPE;
			}
		});
		menuItemLeitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellesGatter = Gatter.LEITUNG;
			}
		});

		JPanel toggleModus = new JPanel();

		JRadioButton baumodus = new JRadioButton("Baumodus", true);
		baumodus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellerModus = Modus.BAUMODUS;
			}
		});
		JRadioButton simmulationsmodus = new JRadioButton("Simmulationsmodus", false);
		simmulationsmodus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.aktuellerModus = Modus.SIMULATIONSMODUS;
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(baumodus);
		group.add(simmulationsmodus);

		toggleModus.add(baumodus);
		toggleModus.add(simmulationsmodus);
		Menuleiste.add(toggleModus);

		Menuleiste.add(Box.createHorizontalGlue());
		JButton undoButton = new JButton("Undo");
		JButton clearButton = new JButton("Clear");
		JButton runButton = new JButton("Run");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.removeLastGatter();
			}
		});
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.clear();
			}
		});
		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.run();
			}
		});
		Menuleiste.add(undoButton);
		Menuleiste.add(clearButton);
		Menuleiste.add(runButton);
		add(Menuleiste, BorderLayout.NORTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

}
