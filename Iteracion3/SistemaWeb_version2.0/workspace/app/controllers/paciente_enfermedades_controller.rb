class PacienteEnfermedadesController < ApplicationController
  before_action :authenticate_medico!
  before_action :authorize_medico, only: [:show, :edit, :update, :destroy, :index, :new, :create]
  
  def index
    @paciente = Paciente.find(params[:paciente_id])
    if current_medico.pacientes.exists?(params[:paciente_id])
     
      @enfermedads = @paciente.enfermedads.paginate(page: params[:page], :per_page => 15)
    else
      respond_to do |format|
        format.html { redirect_to paciente_enfermedads_path(@paciente), notice: 'Enfermedad fue asignada exitosamente.' }
      end
    end
  end

  def new
    @enfermedads = Enfermedad.all
    @paciente = Paciente.find(params[:paciente_id])
    @enfermedad = @paciente.enfermedads.new
  end
  
  
  def create
    @paciente = Paciente.find(params[:paciente_id])
    respond_to do |format|
       if @paciente.enfermedads.exists?(params[:enfermedad][:id])
          format.html { redirect_to new_paciente_enfermedad_path(@paciente), notice: 'No se aceptan enfermedades ya ingresadas'}
       else
         @paciente.enfermedads << Enfermedad.find(params[:enfermedad][:id])
         format.html { redirect_to paciente_enfermedads_path(@paciente), notice: 'Enfermedad fue asignada exitosamente.' }
       end
     end
  end
  
  def destroy
     @paciente = Paciente.find(params[:paciente_id])
     @enfermedad = @paciente.enfermedads.find(params[:id])
     @paciente.enfermedads.delete(@enfermedad)
      respond_to do |format|
      format.html { redirect_to paciente_enfermedads_path(@paciente), notice: 'Enfermedad fue eliminada del paciente exitosamente.' }
      format.json { head :no_content }
    end
  end
  
  private 
  
    def authorize_medico
      if current_medico.pacientes.exists?(['id LIKE ?', "%#{params[:paciente_id]}%"])
      else
        respond_to do |format|
          format.html { redirect_to pacientes_path }
        end
      end
    end
 
end
