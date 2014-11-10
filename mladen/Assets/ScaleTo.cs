using UnityEngine;
using System.Collections;

public class ScaleTo : MonoBehaviour {
	public Vector3 scale;
	public float time;
	private bool isScaled = false;

	private Vector3 scalePerSec;
	void Start() {
		scalePerSec = (scale - transform.localScale) / time; 
	}
	// Update is called once per frame
	void Update () {
		if (isScaled) return;
		transform.localScale += scalePerSec*Time.deltaTime;
		if ((scalePerSec.x > 0 && transform.localScale.x > scale.x) || (scalePerSec.x < 0 && transform.localScale.x < scale.x)) {
			isScaled = true;
		}
	}
}
