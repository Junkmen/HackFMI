using UnityEngine;
using System.Collections;

public class RightHand : MonoBehaviour {
	bool moveDown;
	bool moveUp;
	
	public GameObject player;
	
	private Rigidbody rig;
	// Use this for initialization
	void Start () {
		moveDown= false;
		moveUp = true;
		rig = player.GetComponent<Rigidbody>();
	}
	
	// Update is called once per frame
	void Update () {

		if(moveUp) {
			this.transform.Rotate (0f, 2.8f, 0f);
			//Debug.Log("moving up");
		} else if(moveDown) {
			this.transform.Rotate (0f, -2.8f, 0f);
			//Debug.Log("moving down");
		}

		if (rig.velocity.z < 0) {
			moveUp = true;
			moveDown = false;
		} else if (rig.velocity.z > 0) {
			moveDown = true;
			moveUp = false;
		}

		if (this.transform.rotation.eulerAngles.y <= 1 ) {
			moveUp = false;
		} else if (this.transform.rotation.eulerAngles.y >= 160) {
			moveDown = false;
		}
	}
}
