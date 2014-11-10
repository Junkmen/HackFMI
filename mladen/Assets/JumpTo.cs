using UnityEngine;
using System.Collections;

public class JumpTo : MonoBehaviour {

	public Transform target,prevTarget;
	public Collider playerCollider;

	private Vector3 prevPos;
	 
	public void setTarget(Transform target) {
		this.target = target;
	}
	public void JumpToTarget() {
	
		if (prevTarget != null && prevTarget == this.target)
						return;
		prevTarget = this.target;
		rigidbody.velocity = transform.forward*10f + new Vector3(target.position.x - transform.position.x,0f,0f)/1.8f;
		Debug.Log ((target.position.x - transform.position.x)/1.8f);
		playerCollider.enabled = false;
		Debug.Log ("Jumped to" + target.position);
	}
	void Start() {
		//JumpToTarget (); 
		prevPos = new Vector3 (transform.position.x, transform.position.y, transform.position.z);
	}

	void Update() {
		if (transform.position.z > target.position.z + 1f && transform.position.z  < target.position.z + 1.3f && rigidbody.velocity.z < 0) {
			Debug.Log ("YEAH");
			if (transform.position.x > target.position.x -0.6f && transform.position.x < target.position.x + 0.6f)
				playerCollider.enabled = true;
		}
		if (rigidbody.velocity.z < -4f)
						rigidbody.velocity = new Vector3 (rigidbody.velocity.x, rigidbody.velocity.y, -4f);
	}
	void LateUpdate() {
		if (playerCollider.enabled)
				if (prevPos == transform.position)
						JumpToTarget ();
		prevPos.Set(transform.position.x, transform.position.y, transform.position.z);
	}
}
