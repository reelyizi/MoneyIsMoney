
public class YapiKredi extends Banka {

	@Override
	public double IhtiyacKredisi(int ParaMiktar) {
		if (ParaMiktar > 0 && ParaMiktar < 10000) {
			SetInterestRate(3.14);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(2.84);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(2.79);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(2.72);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(2.69);
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
			SetInterestRate(2.49);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 10001 && ParaMiktar < 50000) {
			SetInterestRate(2.49);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 50001 && ParaMiktar < 100000) {
			SetInterestRate(2.49);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		else if (ParaMiktar > 100001 && ParaMiktar < 500000) {
			SetInterestRate(2.49);
			return Hesapla(ParaMiktar, GetInterestRate());
		}

		SetInterestRate(2.49);
		return Hesapla(ParaMiktar, GetInterestRate());
	}

	@Override
	double Hesapla(int ParaMiktar, double faiz) {
		// TODO Auto-generated method stub
		return (ParaMiktar * faiz) + ParaMiktar;
	}

}