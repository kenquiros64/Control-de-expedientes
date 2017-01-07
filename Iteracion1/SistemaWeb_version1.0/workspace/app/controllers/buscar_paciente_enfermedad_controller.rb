class BuscarPacienteEnfermedadController < ApplicationController
  def index
    @enfermedads = Enfermedad.all
  end

  def create
    word = "%#{params[:keyword]}%"
    @enfermedad = Enfermedad.find(params[:id])
    @pacientes = @enfermedad.pacientes
    
    respond_to do |format|
      format.html { redirect_to  buscar_paciente_enfermedad_index_path }
      format.json { render json: @pacientes }
      format.js
    end
  end
end
