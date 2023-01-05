
public abstract class Banka{
	
	double interestRate = 0;
	double GetInterestRate() {
		return interestRate;
	};
	void SetInterestRate(Double value) {
		interestRate = value;
	};
	int IhtiyacKredisi;
	int KonutKredisi;
	int TasitKredisi;
	
	abstract double Hesapla(int ParaMiktar, double faiz);

	abstract double IhtiyacKredisi(int money);
}
