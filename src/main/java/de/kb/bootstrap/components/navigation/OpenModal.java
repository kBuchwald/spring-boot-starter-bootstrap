package de.kb.bootstrap.components.navigation;

public class OpenModal implements NavigateTo{
	private final String modalId;
	
	public OpenModal(String modalId) {
		this.modalId = modalId;
	}

	public String getModalId() {
		return this.modalId;
	}

	@Override
	public <T extends NavigateToVisitor> T accept(T visitor) {
		visitor.visit(this);
		return visitor;
	}
}