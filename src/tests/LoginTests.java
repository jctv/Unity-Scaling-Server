package tests;

import generic.BaseTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.commons.lang3.StringUtils;
import pages.Login;

public class LoginTests extends BaseTest {

	Login Nav;

	public LoginTests() {
		super();

	}

	@BeforeMethod
	public void setUp() {

	}

	@Test
	public void login() {
/*		int[] a = new int[8];
		a[0] = -1;
		a[1] = 3;
		a[2] = -4;
		a[3] = 5;
		a[4] = 1;
		a[5] = -6;
		a[6] = 2;
		a[7] = 1;

		int P = a.length - 1;

		while (P >= 0) {

			if (getLHS(P, a) == getRHS(P, a)) {
				System.out.println("es indice equilibrio " + P);
				break;
			} else {
				P--;
			}

		}
*/ 
		
	int N = 6;							// 1
for(int i = 1; i <= N*3; i++){			// n
 System.out.println(StringUtils.repeat(" ", N - i) + StringUtils.repeat("#", i));
}
	}

	private int getLHS(int pindex, int[] a) {
		int sum = 0;
		for (int i = 0; i < pindex; i++) {
			sum += a[i];
		}
		 
		return sum;
	}
	

	private int getRHS(int pindex, int[] a) {
		int sum = 0;
		for (int i = pindex + 1; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

}