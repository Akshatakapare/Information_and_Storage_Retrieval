# =========================================
# Feature Extraction in 2D Color Image
# Display all histograms in a single window
# =========================================

import cv2
import numpy as np
from skimage.feature import graycomatrix, graycoprops

# ---------- Step 1: Load Image ----------
image_path = r"C:\Users\aksha\OneDrive\Desktop\ISR ASSIGNMENT B3\wallpaper.jpg"
img = cv2.imread(image_path)

if img is None:
    print("Error: Image not found at the path!")
    exit()

# Display the input image
cv2.imshow("Input Image", img)
cv2.waitKey(0)
cv2.destroyAllWindows()

# ---------- Step 2: Color Histogram ----------
colors = ('b', 'g', 'r')
hist_img = np.zeros((300, 256, 3), dtype=np.uint8)

for i, col in enumerate(colors):
    hist = cv2.calcHist([img], [i], None, [256], [0, 256])
    hist = hist / hist.max()
    for x, y in enumerate(hist):
        color_val = (255 if col=='b' else 0, 255 if col=='g' else 0, 255 if col=='r' else 0)
        cv2.line(hist_img, (x, 300), (x, 300 - int(y*300)), color_val)

# ---------- Step 3: Gray-Level Histogram ----------
gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
hist_gray = cv2.calcHist([gray], [0], None, [256], [0, 256])
hist_gray = hist_gray / hist_gray.max()

for x, y in enumerate(hist_gray):
    cv2.line(hist_img, (x, 300), (x, 300 - int(y*150)), (200, 200, 200))  # smaller height for gray

# Show combined histogram
cv2.imshow("Combined Histograms (RGB + Gray)", hist_img)
cv2.waitKey(0)
cv2.destroyAllWindows()

# ---------- Step 4: Texture Features (GLCM) ----------
glcm = graycomatrix(gray, distances=[1], angles=[0], levels=256, symmetric=True, normed=True)
features = ['contrast', 'correlation', 'energy', 'homogeneity']

print("\nTexture Features:")
for feat in features:
    value = graycoprops(glcm, feat)[0, 0]
    print(f"{feat.capitalize():<12}: {value:.4f}")
