
public class Akbank extends Banka {

	
	
	@Override
	public double IhtiyacKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(2.43);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(2.22);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(2.16);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(2.12);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(2.10);
		return Hesapla(ParaMiktar, GetInterestRate());
	}

	public double KonutKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(2.05);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(2.05);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(2.05);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(2.05);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(2.05);
		return Hesapla(ParaMiktar, GetInterestRate());
	}

	public double TasitKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(2.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(2.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(2.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(2.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(2.89);
		return Hesapla(ParaMiktar, GetInterestRate());
	}

	@Override
	double Hesapla(int ParaMiktar, double faiz) {
		// TODO Auto-generated method stub
		return (ParaMiktar * faiz) + ParaMiktar;
	}

}
