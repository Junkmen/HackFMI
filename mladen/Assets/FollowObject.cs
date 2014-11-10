using UnityEngine;
using System.Collections;

public class FollowObject : MonoBehaviour {

	public Transform target;
	public string gameObjectTag;
	public bool byYaxis = false;
	Vector3 prevPos;
	void Start () {
		if (target == null && gameObject != null) target = GameObject.FindGameObjectWithTag(gameObjectTag).GetComponent<Transform>();
		prevPos = new Vector3 (target.position.x, target.position.y, target.position.z);
	}
	// Update is called once per frame
	void Update () {
		if (byYaxis) {
			transform.Translate (0f, target.position.y - prevPos.y, 0f);
		} else {
			transform.position = new Vector3(transform.position.x, transform.position.y,transform.position.z +(target.position.z - prevPos.z));
		}
		prevPos.Set (target.position.x, target.position.y, target.position.z);
	}
}
