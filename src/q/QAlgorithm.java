package q;

import java.util.Random;

public class QAlgorithm{
	//	
	public static void main(String [] args){
		Random r = new Random(); 
		int arrayLimiter = 0, totalSlots = 0, i = 0, slot = 0;
		
		double Q = 4, Qfp = 4, Cq = 0.2, num =0;
		double frameEnd = Math.pow(2.0, Q);

		for(int z=1; z<=1000; z+=100){
			
			for(int k =0; k <2000; k++){
				totalSlots =0;
				int n = z;
				do {
					slot =0;
					arrayLimiter = (int) frameEnd;

					// Apply random tag inputs in the frame
					for (i = 0; i < z; i++) {
						int t = r.nextInt(arrayLimiter);
						//					System.out.println("random"+t);
						if(t==0)
							slot++;
					}
					switch(slot) {
					case 0:
						Qfp = Math.max(0, Qfp - Cq);
						break;
					case 1:
						n--;

						break;
					default:
						Qfp = Math.min(15, Qfp + Cq);
						break;
					}

					Q = Math.round(Qfp);
					frameEnd = Math.pow(2.0, Q);
					totalSlots++;

				} while (n > 0);
				//				System.out.println(totalSlots);
				//				System.out.println((double)totalSlots/2000);
				num += (double)totalSlots/2000;
				
//				System.out.println(totalSlots+" num : "+num);
			}
			System.out.println(num);
			num =0;
		}
	}

}

