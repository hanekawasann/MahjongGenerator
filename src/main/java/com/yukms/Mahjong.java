package com.yukms;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;

import org.springframework.core.io.DefaultResourceLoader;

/**
 * @author yukms 2019/2/20
 */
public enum Mahjong {

    CHARACTER_ONE("一万", MahjongConstant.INDEX_CHARACTERS[0]),
    CHARACTER_TWO("二万", MahjongConstant.INDEX_CHARACTERS[1]),
    CHARACTER_THREE("三万", MahjongConstant.INDEX_CHARACTERS[2]),
    CHARACTER_FOUR("四万", MahjongConstant.INDEX_CHARACTERS[3]),
    CHARACTER_FIVE("五万", MahjongConstant.INDEX_CHARACTERS[4]),
    CHARACTER_FIVE_RED("五万", MahjongConstant.INDEX_CHARACTERS[5]),
    CHARACTER_SIX("六万", MahjongConstant.INDEX_CHARACTERS[6]),
    CHARACTER_SEVEN("七万", MahjongConstant.INDEX_CHARACTERS[7]),
    CHARACTER_EIGHT("八万", MahjongConstant.INDEX_CHARACTERS[8]),
    CHARACTER_NINE("九万", MahjongConstant.INDEX_CHARACTERS[9]),
    DOT_ONE("一筒", MahjongConstant.INDEX_DOTS[0]),
    DOT_TWO("二筒", MahjongConstant.INDEX_DOTS[1]),
    DOT_THREE("三筒", MahjongConstant.INDEX_DOTS[2]),
    DOT_FOUR("四筒", MahjongConstant.INDEX_DOTS[3]),
    DOT_FIVE("五筒", MahjongConstant.INDEX_DOTS[4]),
    DOT_FIVE_RED("五筒", MahjongConstant.INDEX_DOTS[5]),
    DOT_SIX("六筒", MahjongConstant.INDEX_DOTS[6]),
    DOT_SEVEN("七筒", MahjongConstant.INDEX_DOTS[7]),
    DOT_EIGHT("八筒", MahjongConstant.INDEX_DOTS[8]),
    DOT_NINE("九筒", MahjongConstant.INDEX_DOTS[9]),
    BAMBOO_ONE("一条", MahjongConstant.INDEX_BAMBOOS[0]),
    BAMBOO_TWO("二条", MahjongConstant.INDEX_BAMBOOS[1]),
    BAMBOO_THREE("三条", MahjongConstant.INDEX_BAMBOOS[2]),
    BAMBOO_FOUR("四条", MahjongConstant.INDEX_BAMBOOS[3]),
    BAMBOO_FIVE("五条", MahjongConstant.INDEX_BAMBOOS[4]),
    BAMBOO_FIVE_RED("五条", MahjongConstant.INDEX_BAMBOOS[5]),
    BAMBOO_SIX("六条", MahjongConstant.INDEX_BAMBOOS[6]),
    BAMBOO_SEVEN("七条", MahjongConstant.INDEX_BAMBOOS[7]),
    BAMBOO_EIGHT("八条", MahjongConstant.INDEX_BAMBOOS[8]),
    BAMBOO_NINE("九条", MahjongConstant.INDEX_BAMBOOS[9]),
    WIND_EAST("东", MahjongConstant.INDEX_WINDS[0]),
    WIND_SOUTH("南", MahjongConstant.INDEX_WINDS[1]),
    WIND_WEST("西", MahjongConstant.INDEX_WINDS[2]),
    WIND_NORTH("北", MahjongConstant.INDEX_WINDS[3]),
    DRAGON_WHITE("白", MahjongConstant.INDEX_DRAGONS[0]),
    DRAGON_GREEN("发", MahjongConstant.INDEX_DRAGONS[1]),
    DRAGON_RED("中", MahjongConstant.INDEX_DRAGONS[2]);

    private String name;
    private ByteArrayOutputStream outputStream;

    public static Mahjong ofName(String name) {
        for (Mahjong mahjong : Mahjong.values()) {
            if (mahjong.getName().equals(name)) {
                return mahjong;
            }
        }
        throw new IllegalArgumentException("name");
    }

    public String getName() {
        return name;
    }

    public ByteArrayOutputStream getOutputStream() {
        return outputStream;
    }

    Mahjong(String name, int[] index) {
        this.name = name;
        this.outputStream = getOutputStream(index);
    }

    private static ByteArrayOutputStream getOutputStream(int[] index) {
        int x = MahjongConstant.X + (MahjongConstant.WIDTH + MahjongConstant.WIDTH_SPACE) * (index[0] - 1);
        int y = MahjongConstant.Y + (MahjongConstant.HEIGHT + MahjongConstant.HEIGHT_SPACE) * (index[1] - 1);
        BufferedImage bufferedImage = MahjongConstant.IMAGE
            .getSubimage(x, y, MahjongConstant.WIDTH, MahjongConstant.HEIGHT);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", outputStream);
        } catch (IOException e) {
        }
        return outputStream;
    }

    private static class MahjongConstant {

        static final int[][] INDEX_CHARACTERS = {//
            { 7, 1 }, { 5, 4 }, { 8, 2 },//
            { 7, 3 }, { 3, 1 }, { 7, 2 }, { 1, 5 },//
            { 2, 4 }, { 3, 3 }, { 4, 1 } };
        static final int[][] INDEX_DOTS = {//
            { 8, 1 }, { 5, 5 }, { 6, 3 },//
            { 8, 3 }, { 1, 2 }, { 5, 1 }, { 2, 1 },//
            { 2, 5 }, { 3, 4 }, { 4, 2 } };
        static final int[][] INDEX_BAMBOOS = {//
            { 5, 2 }, { 6, 2 }, { 6, 4 },//
            { 7, 4 }, { 1, 3 }, { 6, 1 }, { 2, 2 },//
            { 1, 1 }, { 3, 5 }, { 4, 3 } };
        static final int[][] INDEX_WINDS = {//
            { 5, 3 }, { 4, 5 }, { 6, 5 }, { 7, 5 } };
        static final int[][] INDEX_DRAGONS = {//
            { 1, 4 }, { 2, 3 }, { 3, 2 } };

        static final int X = 0;
        static final int Y = 1;
        static final int WIDTH = 80;
        static final int WIDTH_SPACE = 1;
        static final int HEIGHT = 129;
        static final int HEIGHT_SPACE = 1;
        static final BufferedImage IMAGE;

        static {
            BufferedImage bufferedImage;
            try {
                File file = getImage();
                bufferedImage = ImageIO.read(file);
            } catch (IOException e) {
                bufferedImage = null;
            }
            Objects.nonNull(bufferedImage);
            IMAGE = bufferedImage;
        }

        private static File getImage() throws IOException {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            return resourceLoader.getResource("classpath:ui.png").getFile();
        }

    }

}
