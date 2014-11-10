using UnityEngine;
using System.Collections;

public class GUI_Text_script : MonoBehaviour {

	CheckForTouch script;
	string score_str;
	public int score_;

	void Start () {
		script = gameObject.GetComponent<CheckForTouch> ();
		//score_str = script.score.ToString ();
	}
	
	// Update is called once per frame
	void Update () {
		score ();

		//guiText.text = "SCORE: " + script.score;
		//Debug.Log (script.score);
		//guiText.text = "SCORE: " + score_/14;
	}

	void score(){
		score_++; 
	}
}
