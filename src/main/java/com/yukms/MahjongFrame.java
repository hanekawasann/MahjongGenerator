package com.yukms;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.yukms.entity.MahjongCardType;
import com.yukms.util.BufferedImageUtil;

/**
 * @author yukms 2019\2\21 0021.
 */
public class MahjongFrame extends JFrame {
    private static final int ROWS = 1;
    private static final int COLS = 12;
    private static final int HGAP = 2;
    private static final int VGAP = 10;
    public static final String FORMAT_NAME = "png";
    private JPanel creatPanel = creatCreatPanel();
    private List<MahjongCardType> creatCards = new ArrayList<>();

    private MahjongFrame() throws HeadlessException {
        setJMenuBar(creatJMenuBar());
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEFT));
        container.add(creatCharacterPanel());
        container.add(creatDotPanel());
        container.add(creatBambooPanel());
        container.add(creatWindPanel());
        container.add(creatPanel);
    }

    private JMenuBar creatJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu saveJMenu = new JMenu("保存为");
        saveJMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                String filePath = selectFilePath();
                saveCreatCards(filePath);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        jMenuBar.add(saveJMenu);
        return jMenuBar;
    }

    private String selectFilePath() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.showDialog(new JLabel(), "选择");
        return jFileChooser.getSelectedFile().getAbsolutePath();
    }

    private void saveCreatCards(String path) {
        int width = creatPanel.getWidth();
        int height = creatPanel.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        creatPanel.paint(g2);
        try {
            File dst = new File(path + "\\" + getFileName() + "." + FORMAT_NAME);
            ImageIO.write(image, FORMAT_NAME, dst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName() {
        return String.valueOf(Instant.now().getEpochSecond());
    }

    private JPanel creatCharacterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        panel.add(getLabel("万子"));
        panel.add(getLabel(MahjongCardType.CHARACTER_ONE));
        panel.add(getLabel(MahjongCardType.CHARACTER_TWO));
        panel.add(getLabel(MahjongCardType.CHARACTER_THREE));
        panel.add(getLabel(MahjongCardType.CHARACTER_FOUR));
        panel.add(getLabel(MahjongCardType.CHARACTER_FIVE));
        panel.add(getLabel(MahjongCardType.CHARACTER_FIVE_RED));
        panel.add(getLabel(MahjongCardType.CHARACTER_SIX));
        panel.add(getLabel(MahjongCardType.CHARACTER_SEVEN));
        panel.add(getLabel(MahjongCardType.CHARACTER_EIGHT));
        panel.add(getLabel(MahjongCardType.CHARACTER_NINE));
        return panel;
    }

    private JPanel creatDotPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        panel.add(getLabel("饼子"));
        panel.add(getLabel(MahjongCardType.DOT_ONE));
        panel.add(getLabel(MahjongCardType.DOT_TWO));
        panel.add(getLabel(MahjongCardType.DOT_THREE));
        panel.add(getLabel(MahjongCardType.DOT_FOUR));
        panel.add(getLabel(MahjongCardType.DOT_FIVE));
        panel.add(getLabel(MahjongCardType.DOT_FIVE_RED));
        panel.add(getLabel(MahjongCardType.DOT_SIX));
        panel.add(getLabel(MahjongCardType.DOT_SEVEN));
        panel.add(getLabel(MahjongCardType.DOT_EIGHT));
        panel.add(getLabel(MahjongCardType.DOT_NINE));
        return panel;
    }

    private JPanel creatBambooPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        panel.add(getLabel("索子"));
        panel.add(getLabel(MahjongCardType.BAMBOO_ONE));
        panel.add(getLabel(MahjongCardType.BAMBOO_TWO));
        panel.add(getLabel(MahjongCardType.BAMBOO_THREE));
        panel.add(getLabel(MahjongCardType.BAMBOO_FOUR));
        panel.add(getLabel(MahjongCardType.BAMBOO_FIVE));
        panel.add(getLabel(MahjongCardType.BAMBOO_FIVE_RED));
        panel.add(getLabel(MahjongCardType.BAMBOO_SIX));
        panel.add(getLabel(MahjongCardType.BAMBOO_SEVEN));
        panel.add(getLabel(MahjongCardType.BAMBOO_EIGHT));
        panel.add(getLabel(MahjongCardType.BAMBOO_NINE));
        return panel;
    }

    private JPanel creatWindPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        panel.add(getLabel("字牌"));
        panel.add(getLabel(MahjongCardType.WIND_EAST));
        panel.add(getLabel(MahjongCardType.WIND_SOUTH));
        panel.add(getLabel(MahjongCardType.WIND_WEST));
        panel.add(getLabel(MahjongCardType.WIND_NORTH));
        panel.add(getLabel(MahjongCardType.DRAGON_WHITE));
        panel.add(getLabel(MahjongCardType.DRAGON_GREEN));
        panel.add(getLabel(MahjongCardType.DRAGON_RED));
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        panel.add(getEmptyLabel());
        return panel;
    }

    private JPanel creatCreatPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(ROWS, COLS, 0, VGAP));
        return panel;
    }

    private JLabel getEmptyLabel() {
        return new JLabel();
    }

    private JLabel getLabel(String message) {
        JLabel label = new JLabel();
        label.setText(message);
        return label;
    }

    private JLabel getLabel(MahjongCardType cardType) {
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(BufferedImageUtil.getOutputStream(cardType.getImage()).toByteArray()));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isAdd(cardType)) {
                    creatCards.add(cardType);
                    repaintcreatPanel();
                }
            }
        });
        return label;
    }

    private boolean isAdd(MahjongCardType cardType) {
        if (creatCards.size() >= 13) {
            // 牌组不超过13张
            return false;
        }
        int cardSize = getCardSize(cardType);
        if ((cardType.equals(MahjongCardType.CHARACTER_FIVE_RED) || cardType.equals(MahjongCardType.DOT_FIVE_RED) ||
            cardType.equals(MahjongCardType.BAMBOO_FIVE_RED)) && cardSize >= 1) {
            // 赤宝牌只有一张
            return false;
        } else if ((cardType.equals(MahjongCardType.CHARACTER_FIVE) || cardType.equals(MahjongCardType.DOT_FIVE) ||
            cardType.equals(MahjongCardType.BAMBOO_FIVE)) && cardSize >= 3) {
            // 赤宝牌对应的牌不超过3张
            return false;
        } else {
            // 其余的牌超过4张
            return cardSize < 4;
        }
    }

    private int getCardSize(MahjongCardType src) {
        int size = 0;
        for (MahjongCardType cardType : creatCards) {
            if (cardType.equals(src)) {
                size++;
            }
        }
        return size;
    }

    private JLabel getCreatLabel(MahjongCardType cardType) {
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(BufferedImageUtil.getOutputStream(cardType.getImage()).toByteArray()));
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                creatCards.remove(cardType);
                repaintcreatPanel();
            }
        });
        return label;
    }

    private void repaintcreatPanel() {
        creatCards.sort(Comparator.comparingInt(Enum::ordinal));
        creatPanel.removeAll();
        creatCards.forEach(mahjongCardType->creatPanel.add(getCreatLabel(mahjongCardType)));
        revalidate();
    }

    public static void main(String[] args) {
        MahjongFrame frame = new MahjongFrame();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
