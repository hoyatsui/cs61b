import synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        int length = keyboard.length();
        GuitarString[] strings = new GuitarString[length];
        for (int i = 0; i < length; i++) {
            strings[i] = new synthesizer.GuitarString(440 * Math.pow(2, ((i - 24) / 12)));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }
            double sample = 0.0;
            for (int i = 0; i < length; i++) {
                sample += strings[i].sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < length; i++) {
                strings[i].tic();
            }

        }
    }
}
