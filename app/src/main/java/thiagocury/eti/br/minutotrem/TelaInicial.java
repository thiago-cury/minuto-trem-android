package thiagocury.eti.br.minutotrem;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.util.ArrayList;

import thiagocury.eti.br.minutotrem.misc.Estacao;

public class TelaInicial extends AppCompatActivity {

	//Menu
	private Drawer result = null;

	//Toolbar
	private Toolbar toolbar;

	protected static ArrayList<Estacao> ests = null;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_tela_inicial);
		toolbar = (Toolbar) findViewById (R.id.toolbar);
		setSupportActionBar (toolbar);

		//##MENU
		//Com o cabeçalho
		//####################### SÓ O CABEÇALHO #######################
		AccountHeader headerResult = new AccountHeaderBuilder ().withActivity (this).withHeaderBackground (R.drawable.menu).addProfiles (
				// new ProfileDrawerItem ().withName("Thiago Cury").withEmail("thiagocury@gmail.com").withIcon(getResources().getDrawable(R.mipmap.ic_people))
		).withOnAccountHeaderListener (new AccountHeader.OnAccountHeaderListener () {
			@Override
			public boolean onProfileChanged (View view, IProfile profile, boolean currentProfile) {
				return false;
			}
		}).build ();

		//Menu
		//####################### SÓ O MENU #######################
		result = new DrawerBuilder ().withActivity (this).withToolbar (toolbar)
				//.withSelectedItem(10) //criar sem deixar pré selecionado
				.withAccountHeader (headerResult).addDrawerItems (
						new PrimaryDrawerItem ().withName (R.string.menu_tela_inicial).withIdentifier (10).withIcon (R.mipmap.ic_home),
						new DividerDrawerItem (),
						new PrimaryDrawerItem ().withName (R.string.menu_achados_e_perdidos).withIdentifier (20).withIcon (R.mipmap.ic_achados),
						new PrimaryDrawerItem ().withName (R.string.menu_canais_atendimento).withIdentifier (30).withIcon (R.mipmap.ic_atendimento),
						new PrimaryDrawerItem ().withName (R.string.menu_cartao_sim).withIdentifier (40).withIcon (R.mipmap.ic_sim),
						new DividerDrawerItem (),
						//new PrimaryDrawerItem().withName(R.string.menu_verificar_ciclistas).withIdentifier(50).withIcon(R.mipmap.ic_ciclista),
						//new PrimaryDrawerItem().withName(R.string.menu_verificar_etiquetas).withIdentifier(60).withIcon(R.mipmap.ic_etiqueta),
						new PrimaryDrawerItem().withName(R.string.menu_verificar_estacao_proxima).withIdentifier(70).withIcon(R.mipmap.ic_estacao_proxima),
						new PrimaryDrawerItem().withName(R.string.menu_informacoes_estacoes).withIdentifier(80).withIcon(R.mipmap.ic_sobre),

						/*new PrimaryDrawerItem().withName(R.string.menu_verificar_estacao_proxima).withIdentifier(20).withIcon(R.mipmap.ic_launcher),
						new PrimaryDrawerItem().withName(R.string.menu_modo_trem).withIdentifier(30).withIcon(R.mipmap.ic_launcher),
                        V new PrimaryDrawerItem().withName(R.string.menu_verificar_etiquetas).withIdentifier(50).withIcon(R.mipmap.ic_launcher),
                        V new PrimaryDrawerItem().withName(R.string.menu_verificar_ciclistas).withIdentifier(60).withIcon(R.mipmap.ic_launcher),
                        V new PrimaryDrawerItem().withName(R.string.menu_canais_atendimento).withIdentifier(70).withIcon(R.mipmap.ic_launcher),
                        V new PrimaryDrawerItem().withName(R.string.menu_cartao_sim).withIdentifier(80).withIcon(R.mipmap.ic_launcher),
                        V new PrimaryDrawerItem().withName(R.string.menu_achados_e_perdidos).withIdentifier(90).withIcon(R.mipmap.ic_launcher),
                        new PrimaryDrawerItem().withName(R.string.menu_noticias).withIdentifier(100).withIcon(R.mipmap.ic_launcher),*/
						new PrimaryDrawerItem ().withName (R.string.menu_sobre).withIdentifier (888).withIcon (R.mipmap.ic_sobre), new PrimaryDrawerItem ().withName (R.string.menu_sair).withIdentifier (999).withIcon (R.mipmap.ic_sair)).withOnDrawerItemClickListener (new Drawer.OnDrawerItemClickListener () {

					@Override
					public boolean onItemClick (AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
						switch (iDrawerItem.getIdentifier ()) {
							case 10:
								//getSupportActionBar().setDisplayHomeAsUpEnabled(false);
								//result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
								FragmentTransaction ft10 = getFragmentManager ().beginTransaction ();
								FragHome home = new FragHome ();
								ft10.replace (R.id.frame1, home);
								ft10.commit ();
								break;
							case 20:
								FragmentTransaction ft20 = getFragmentManager ().beginTransaction ();
								FragAchadosEPerdidos achadosEPerdidos = new FragAchadosEPerdidos ();
								ft20.replace (R.id.frame1, achadosEPerdidos);
								ft20.commit ();
								break;
							case 30:
								FragmentTransaction ft30 = getFragmentManager ().beginTransaction ();
								FragAtendimento atendimento = new FragAtendimento ();
								ft30.replace (R.id.frame1, atendimento);
								ft30.commit ();
								break;
							case 40:
								FragmentTransaction ft40 = getFragmentManager ().beginTransaction ();
								FragCartaoSim cartaoSim = new FragCartaoSim ();
								ft40.replace (R.id.frame1, cartaoSim);
								ft40.commit ();
								break;
							case 50:
								FragmentTransaction ft50 = getFragmentManager ().beginTransaction ();
								FragGuiaCiclista guiaCiclista = new FragGuiaCiclista ();
								ft50.replace (R.id.frame1, guiaCiclista);
								ft50.commit ();
								break;
							case 60:
								FragmentTransaction ft60 = getFragmentManager ().beginTransaction ();
								FragEtiquetaUrbana etiquetaUrbana = new FragEtiquetaUrbana ();
								ft60.replace (R.id.frame1, etiquetaUrbana);
								ft60.commit ();
								break;
							case 70:
								Intent it = new Intent(TelaInicial.this, TelaMapaEstacoes.class);
								startActivity(it);
								break;
							case 80:
								FragmentTransaction ft80 = getFragmentManager ().beginTransaction ();
								FragEstacoes fragEstacoes = new FragEstacoes();
								ft80.replace (R.id.frame1, fragEstacoes);
								ft80.commit ();
								break;

							case 888:
								FragmentTransaction ft888 = getFragmentManager ().beginTransaction ();
								FragSobre sobre = new FragSobre ();
								ft888.replace (R.id.frame1, sobre);
								//ft9.addToBackStack(null);
								ft888.commit ();
								break;

							case 999:
								finish ();
								//onBackPressed();
								break;
						}//fecha switch
						return false;
					}
				}).withOnDrawerListener (new Drawer.OnDrawerListener () {
					@Override
					public void onDrawerOpened (View view) {
					}

					@Override
					public void onDrawerClosed (View view) {
					}

					@Override
					public void onDrawerSlide (View view, float v) {
					}
				}).build ();
		result.setSelectionByIdentifier (10);//Pré selecionando o primeiro item
		//## Fim menu

		ests = new ArrayList<>();

		//Firebase
		FirebaseApp.initializeApp(TelaInicial.this);
		final FirebaseDatabase db = FirebaseDatabase.getInstance();
		final DatabaseReference banco = db.getReference("estacoes");

		banco.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				ests.clear();
				for(DataSnapshot data: dataSnapshot.getChildren()){
					Estacao e = data.getValue(Estacao.class);
					e.setKey(data.getKey()); //Colocando key manualmente no objeto
					ests.add(e);
				}
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
			}
		});

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = this.getWindow ();
			window.clearFlags (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags (WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor (getResources ().getColor (R.color.blue700));
		}//fecha if
	}//fecha oncreate
}//fecha classe