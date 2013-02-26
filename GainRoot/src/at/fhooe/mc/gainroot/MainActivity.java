package at.fhooe.mc.gainroot;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import at.fhooe.mc.gainroot.essentials.Exploit;
import at.fhooe.mc.gainroot.essentials.ExploitAdapter;
import at.fhooe.mc.gainroot.exploits.GingerBreak;
import at.fhooe.mc.gainroot.exploits.Root4x;
import at.fhooe.mc.gainroot.exploits.Unroot;
import at.fhooe.mc.gainroot.exploits.Zygote;

/**
 * Show all {@link Exploit}s which are defined in {@link #initExploits()} method
 * and give the user the opportunity to get detailed information by clicking on
 * a item.
 * 
 * @author Gerald Schoiber
 * @version 1.0
 * @see ArrayList, ListActivity, Intent, Bundle, Log, View, ListView, Exploit,
 *      ExploitAdapter
 */
public class MainActivity extends ListActivity {

	/**
	 * Used for debugging to determine which class message to the log.
	 * 
	 * @see String, Log
	 */
	private final String TAG = "MainClass";
	/**
	 * Hold all exploits which are shown in {@link MainActivity}.
	 * 
	 * @see String
	 */
	private ArrayList<Exploit> m_exploits;
	/**
	 * Manage all exploits from {@link #m_exploits} in a graphical manner.
	 * 
	 * @see ExploitAdapter
	 */
	private ExploitAdapter m_adapter;

	/**
	 * Add all exploits which should shown in {@link MainActivity} into the
	 * {@link #m_exploits} variable.
	 * 
	 * @see Exploit, ArrayList
	 */
	private void initExploits() {
		m_exploits = new ArrayList<Exploit>();

		// add exploits
		m_exploits.add(new GingerBreak());
		m_exploits.add(new Zygote());
		m_exploits.add(new Root4x());

		// add unroot device
		m_exploits.add(new Unroot());
	}

	/**
	 * Set the ContentView 'R.layout.activity_main' to {@link MainActivity},
	 * call the {@link #initExploits()} method and initialize the
	 * {@link #m_adapter} which handle the {@link Exploit} items in the
	 * {@link android.app.ListActivity}.
	 * 
	 * @param _savedInstanceState
	 *            Used in the super class.
	 * @see Bundle, initExploits(), m_adapter, m_exploits
	 */
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.activity_main);

		// set all exploits
		initExploits();
		m_adapter = new ExploitAdapter(this, R.layout.exploit_item, m_exploits);
		setListAdapter(m_adapter);

		m_adapter.notifyDataSetChanged();
		if (m_exploits != null && m_exploits.size() > 0)
			for (int n = 0; n < m_exploits.size(); n++) {
				m_adapter.add(m_exploits.get(n));
				m_adapter.notifyDataSetChanged();
			}
	}

	/**
	 * Start the {@link Exploit} activity which was clicked.
	 * 
	 * @param _listview
	 *            Not used.
	 * @param _view
	 *            Not used.
	 * @param _position
	 *            Position of clicked item.
	 * @param _id
	 *            Not used.
	 * @see Integer, Long}, View, ListView, Intent, Context, TAG, m_exploits}
	 */
	@Override
	protected void onListItemClick(ListView _listview, View _view,
			int _position, long _id) {
		Log.i(TAG, "exploit: " + m_exploits.get(_position).getName() + " / "
				+ m_exploits.get(_position).getId());
		startActivity(new Intent(getApplicationContext(), m_exploits.get(
				_position).getClass()));
	}
}
