class PacienteObservacionsController < ApplicationController
   before_action :authenticate_medico!
  
  def index
    @paciente = Paciente.find(params[:paciente_id])
    @observacions = @paciente.observacions.paginate(page: params[:page], :per_page => 15)
  end

  def new
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = @paciente.observacions.new
  end
  
  def create 
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = Observacion.new
    @observacion.descripcion = params[:observacion][:descripcion]
    @observacion.medico_id = current_medico.id
    @observacion.created_at = Time.now
    respond_to do |format|
       if @observacion.save
         @paciente.observacions << @observacion
         format.html { redirect_to paciente_observacions_path(@paciente), notice: 'Observación fue creada exitosamente.' }
       else
         format.html { redirect_to new_paciente_observacion_path(@paciente), notice: 'Error al crear observación'}
       end
     end
  end
  
  def show
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = Observacion.find(params[:id])
  end

  def edit
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = Observacion.find(params[:id])
  end
  
  def update
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = Observacion.find(params[:id])
    @observacion.descripcion = params[:observacion][:descripcion]
    @observacion.medico_id = current_medico.id
    @observacion.created_at = Time.now
    respond_to do |format|
       if @observacion.update(observacion_params)
         format.html { redirect_to paciente_observacions_path(@paciente), notice: 'Observación fue actualizada exitosamente.' }
       else
         format.html { redirect_to new_paciente_observacion_path(@paciente), notice: 'Error al crear observación'}
       end
     end
  end

  def destroy
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = @paciente.observacions.find(params[:id])
    @paciente.observacions.delete(@observacion)
      respond_to do |format|
      format.html { redirect_to paciente_observacions_path(@paciente), notice: 'Observación fue eliminada exitosamente.' }
      format.json { head :no_content }
    end
  end
  
  
  private
  
    def observacion_params
      params.require(:observacion).permit(:descripcion)
    end
    
end
