using UnityEngine;
using System.Collections;

public class RedirectToNextPlatform : MonoBehaviour {

	public Collider trigger;
	public Transform target;
	public ParticleSystem pSystem;

	private bool passed;

	private Renderer renderer;

	void Start() {
		renderer = gameObject.GetComponent<Collector>().getRenderer();
	}

	void OnTriggerEnter (Collider other) {
		if (target != null) {
			other.gameObject.GetComponent<JumpTo> ().setTarget(target);
			passed = true;
			pSystem.Play();
		}
	}
	public void setTarget(Transform target) {
		this.target = target;
	}
	void Update() {
		if (passed) 
			if (!renderer.isVisible)
				Destroy(gameObject);
	}

}
