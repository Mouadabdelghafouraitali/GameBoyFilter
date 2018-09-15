# GameBoyFilter
Create 8-Bit Style Graphics with Authentic Gameboy Colors


---

## Screenshots

<img src="https://i.imgur.com/mUM4q2f.jpg" width="550">
<img src="https://i.imgur.com/rbe7rSN.jpg" width="550">

---

## Usage:


```groovy
 if(preview!=null){
                    GameBoyDithering gameBoyDithering = new GameBoyDithering();
                    BitmapHelper bitmapHelper = new BitmapHelper();
                    FinalBitmap = bitmapHelper.getResized(preview, 310, false);
                    FinalBitmap = gameBoyDithering.ordered2By2Bayer(FinalBitmap);
                    FinalBitmap = bitmapHelper.ContrastBrightness(FinalBitmap, 1, -24);
                    FinalBitmap = bitmapHelper.changeColor(FinalBitmap, getResources().getColor(R.color.gameboycolor));
                    img.setImageBitmap(FinalBitmap);
                }
```
