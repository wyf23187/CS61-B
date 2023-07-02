package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHeroLite {
    public static final double CONCERT_A = 440.0;
    public static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringA = new GuitarString(CONCERT_A);
        GuitarString stringC = new GuitarString(CONCERT_C);
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < 37; i++) {
            strings[i] = new GuitarString(440.0 * Math.pow(2.0, (i - 24.0) / 12.0));
        }
        /* the main input loop */
        while(true){
            if(StdDraw.hasNextKeyTyped()){
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if(index != -1){
                    strings[index].pluck();
                }
            }
            double sample = 0.0;
            for(int i = 0; i < 37; i++){
                sample += strings[i].sample();
            }
            StdAudio.play(sample);
            for(int i = 0; i < 37; i++){
                strings[i].tic();
            }
        }

//        while (true) {
//
//            /* check if the user has typed a key; if so, process it */
//            if (StdDraw.hasNextKeyTyped()) {
//                char key = StdDraw.nextKeyTyped();
//                if (key == 'a') {
//                    stringA.pluck();
//                } else if (key == 'c') {
//                    stringC.pluck();
//                }
//            }
//
//            /* compute the superposition of samples */
//            double sample = stringA.sample() + stringC.sample();
//
//            /* play the sample on standard audio */
//            StdAudio.play(sample);
//
//            /* advance the simulation of each guitar string by one step */
//            stringA.tic();
//            stringC.tic();
//        }
    }
}

