/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior

import java.util.*;
import acm.graphics.*;

public class ImageShopAlgorithms implements ImageShopAlgorithmsInterface {

	public GImage flipHorizontal(GImage source) {
		int[][] pixelsSrc = source.getPixelArray();
		int[][] pixels = source.getPixelArray();
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				pixels[i][j] = pixelsSrc[i][pixels[0].length-j-1];
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage rotateLeft(GImage source) {
		int[][] pixelsSrc = source.getPixelArray();
		int[][] pixels = new int [pixelsSrc[0].length][pixelsSrc.length];
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				pixels[i][j] = pixelsSrc[j][pixelsSrc[0].length-i-1];
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage rotateRight(GImage source) {
		int[][] pixelsSrc = source.getPixelArray();
		int[][] pixels = new int [pixelsSrc[0].length][pixelsSrc.length];
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				pixels[i][j] = pixelsSrc[pixelsSrc.length-j-1][i];
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage greenScreen(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				int r = GImage.getRed(pixels[i][j]);
				int g = GImage.getGreen(pixels[i][j]);
				int b = GImage.getBlue(pixels[i][j]);
				if (g > 2 * Math.max(r, b)) {
					pixels[i][j] = GImage.createRGBPixel(r, g, b, 0);
				}
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage equalize(GImage source) {
		// 1) Compute the luminosity histogram for the source image
		int[] luHist = new int[256];
		int[][] pixels = source.getPixelArray();
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				int r = GImage.getRed(pixels[i][j]);
				int g = GImage.getGreen(pixels[i][j]);
				int b = GImage.getBlue(pixels[i][j]);
				luHist[computeLuminosity(r, g, b)]++;
			}
		}
		// 2) Compute the cumulative luminosity histogram from the luminosity histogram
		int[] cuHist = new int[256];
		cuHist[0] = luHist[0];
		for (int i = 1; i < 256; i++) {
			cuHist[i] = luHist[i] + cuHist[i-1];
		}
		// 3) Use the cumulative luminosity histogram to modify each pixel to increase contrast
		int total = pixels.length * pixels[0].length;
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				int r = GImage.getRed(pixels[i][j]);
				int g = GImage.getGreen(pixels[i][j]);
				int b = GImage.getBlue(pixels[i][j]);
				int lu = (int)(255* ((double)cuHist[computeLuminosity(r, g, b)] / (double) total));
				pixels[i][j] = GImage.createRGBPixel(lu, lu, lu);
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage negative(GImage source) {
		int[][] pixels = source.getPixelArray();
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				int r = GImage.getRed(pixels[i][j]);
				int g = GImage.getGreen(pixels[i][j]);
				int b = GImage.getBlue(pixels[i][j]);
				r = 255 - r;
				g = 255 - g;
				b = 255 - b;
				pixels[i][j] = GImage.createRGBPixel(r, g, b);
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage translate(GImage source, int dx, int dy) {
		int[][] pixelsSrc = source.getPixelArray();
		int[][] pixels = source.getPixelArray();
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				int ii = (i+dy)%pixels.length;
				int jj = (j+dx)%pixels[0].length;
				if (ii < 0) {
					ii += pixels.length;
				}
				if (jj < 0) {
					jj += pixels[0].length;
				}
				pixels[ii][jj] = pixelsSrc[i][j];
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}

	public GImage blur(GImage source) {
		int[][] pixelsSrc = source.getPixelArray();
		int[][] pixels = source.getPixelArray();
		for (int i = 0; i < pixels.length; i ++) {
			for (int j = 0; j < pixels[0].length; j ++) {
				int neighbor = 0;
				int[] sum = {0, 0, 0};
				for (int ii = i - 1; ii <= i + 1; ii++) {
					for (int jj = j - 1; jj <= j + 1; jj++) {
						if (ii >= 0 && jj >= 0 && ii < pixels.length && jj < pixels[0].length) {
							neighbor++;
							sum[0] += GImage.getRed(pixelsSrc[ii][jj]);
							sum[1] += GImage.getGreen(pixelsSrc[ii][jj]);
							sum[2] += GImage.getBlue(pixelsSrc[ii][jj]);
						}
					}
				}
				pixels[i][j] = GImage.createRGBPixel(sum[0]/neighbor, sum[1]/neighbor, sum[2]/neighbor);
			}
		}
		GImage image = new GImage(pixels);
		return image;
	}
}
