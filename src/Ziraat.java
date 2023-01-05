
public class Ziraat extends Banka {

	@Override
	public double IhtiyacKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(1.79);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(1.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(1.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(1.89);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(1.89);
		return Hesapla(ParaMiktar, GetInterestRate());
	}

	public double KonutKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(1.36);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(1.36);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(1.36);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(1.36);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(1.36);
		return Hesapla(ParaMiktar, GetInterestRate());

	}

	public double TasitKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(1.59);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(1.59);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(1.59);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(1.59);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(1.59);
		return Hesapla(ParaMiktar, GetInterestRate());
	}

	@Override
	double Hesapla(int ParaMiktar, double faiz) {
		// TODO Auto-generated method stub
		return (ParaMiktar * faiz) / 100 + ParaMiktar;
	}

}
