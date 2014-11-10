using UnityEngine;
using System.Collections;

public class destroy : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	


	void OnTriggerExit (Collider other) 
	{
		Destroy(other.gameObject);
		Debug.Log ("Destroing object");
		Application.Quit();
	}


}
