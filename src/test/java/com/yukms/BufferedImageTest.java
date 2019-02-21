package com.yukms;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.yukms.entity.MahjongCardType;
import com.yukms.util.BufferedImageUtil;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class BufferedImageTest {
    private static final int IMAGE_X = 0;
    private static final int IMAGE_Y = 1;
    private static final int MAHJONG_WIDTH = 80;
    private static final int MAHJONG_WIDTH_SPACE = 1;
    private static final int MAHJONG_HEIGHT = 129;
    private static final int MAHJONG_HEIGHT_SPACE = 1;
    private static final int[][] CHARACTERS = {//
        { 7, 1 }, { 5, 4 }, { 8, 2 },//
        { 7, 3 }, { 3, 1 }, { 7, 2 }, { 1, 5 },//
        { 2, 4 }, { 3, 3 }, { 4, 1 } };
    private static final int[][] DOTS = {//
        { 8, 1 }, { 5, 5 }, { 6, 3 },//
        { 8, 3 }, { 1, 2 }, { 5, 1 }, { 2, 1 },//
        { 2, 5 }, { 3, 4 }, { 4, 2 } };
    private static final int[][] BAMBOOS = {//
        { 5, 2 }, { 6, 2 }, { 6, 4 },//
        { 7, 4 }, { 1, 3 }, { 6, 1 }, { 2, 2 },//
        { 1, 1 }, { 3, 5 }, { 4, 3 } };
    private static final int[][] WINDS = {//
        { 5, 3 }, { 4, 5 }, { 6, 5 }, { 7, 5 } };
    private static final int[][] DRAGONS = {//
        { 1, 4 }, { 2, 3 }, { 3, 2 } };

    private static BufferedImage getImage() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:ui.png");
        return ImageIO.read(resource.getFile());
    }

    private static ByteArrayOutputStream toByteArrayOutputStream(BufferedImage image) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        return out;
    }

    private static void showImage(ByteArrayOutputStream out) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(out.toByteArray()));
        frame.getContentPane().add(label);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static void show(MahjongCardType mahjongCardType) throws IOException {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        ByteArrayOutputStream outputStream = BufferedImageUtil.getOutputStream(mahjongCardType.getImage());
        JLabel jLabel = getJLabel(outputStream);
        frame.getContentPane().add(jLabel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static void show() throws IOException {
        BufferedImage image = getImage();
        int x = IMAGE_X + (MAHJONG_WIDTH + MAHJONG_WIDTH_SPACE) * (7 - 1);
        int y = IMAGE_Y + (MAHJONG_HEIGHT + MAHJONG_HEIGHT_SPACE) * (5 - 1);
        BufferedImage subImage = image.getSubimage(x, y, MAHJONG_WIDTH, MAHJONG_HEIGHT);
        ByteArrayOutputStream outputStream = toByteArrayOutputStream(subImage);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JLabel jLabel = getJLabel(outputStream);
        frame.getContentPane().add(jLabel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static void showAll() throws IOException {
        List<int[]> indexs = new ArrayList<>();
        indexs.addAll(Arrays.asList(CHARACTERS));
        indexs.addAll(Arrays.asList(DOTS));
        indexs.addAll(Arrays.asList(BAMBOOS));
        indexs.addAll(Arrays.asList(WINDS));
        indexs.addAll(Arrays.asList(DRAGONS));
        BufferedImage image = getImage();
        List<ByteArrayOutputStream> subImages = new ArrayList<>();
        for (int[] index : indexs) {
            int x = IMAGE_X + (MAHJONG_WIDTH + MAHJONG_WIDTH_SPACE) * (index[0] - 1);
            int y = IMAGE_Y + (MAHJONG_HEIGHT + MAHJONG_HEIGHT_SPACE) * (index[1] - 1);
            BufferedImage subImage = image.getSubimage(x, y, MAHJONG_WIDTH, MAHJONG_HEIGHT);
            ByteArrayOutputStream outputStream = toByteArrayOutputStream(subImage);
            subImages.add(outputStream);
        }

        JFrame frame = new JFrame("Mahjong");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        for (ByteArrayOutputStream subImage : subImages) {
            JLabel jLabel = getJLabel(subImage);
            frame.getContentPane().add(jLabel);
        }
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static JLabel getJLabel(ByteArrayOutputStream byteArrayOutputStream) {
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(byteArrayOutputStream.toByteArray()));
        return label;
    }

    public static void main(String[] args) throws IOException {
        show(MahjongCardType.CHARACTER_SIX);
    }

}