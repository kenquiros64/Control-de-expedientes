class PacienteCitaObservacionsController < ApplicationController
  before_action :authenticate_medico!
  
  def index
    @paciente = Paciente.find(params[:paciente_id])
    @cita = Citum.find(params[:citum_id])
    @observacions = @cita.observacions.paginate(page: params[:page], :per_page => 15)
  end

  def new
    @paciente = Paciente.find(params[:paciente_id])
    @cita = Citum.find(params[:citum_id])
    @observacion = @cita.observacions.new
  end
  
  def create 
    @cita = Citum.find(params[:citum_id])
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = Observacion.new
    @observacion.descripcion = params[:observacion][:descripcion]
    @observacion.medico_id = current_medico.id
    @observacion.created_at = Time.now
    respond_to do |format|
       if @observacion.save
         @cita.observacions << @observacion
         format.html { redirect_to paciente_citum_observacions_path(@paciente,@cita), notice: 'Observación fue creada exitosamente.' }
       else
         format.html { redirect_to new_paciente_citum_observacion_path(@paciente,@cita), notice: 'Error al crear observación'}
       end
     end
  end
  
  def show
    @paciente = Paciente.find(params[:paciente_id])
    @cita = Citum.find(params[:citum_id])
    @observacion = Observacion.find(params[:id])
  end

  def edit
    @paciente = Paciente.find(params[:paciente_id])
    @cita = Citum.find(params[:citum_id])
    @observacion = Observacion.find(params[:id])
  end
  
  def update
    @cita = Citum.find(params[:citum_id])
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = Observacion.find(params[:id])
    @observacion.descripcion = params[:observacion][:descripcion]
    @observacion.medico_id = current_medico.id
    @observacion.created_at = Time.now
    respond_to do |format|
       if @observacion.update(observacion_params)
         format.html { redirect_to paciente_citum_observacions_path(@paciente,@cita), notice: 'Observación fue actualizada exitosamente.' }
       else
         format.html { redirect_to new_paciente_citum_observacion_path(@paciente,@cita), notice: 'Error al crear observación'}
       end
     end
  end

  def destroy
    @cita = Citum.find(params[:citum_id])
    @paciente = Paciente.find(params[:paciente_id])
    @observacion = @cita.observacions.find(params[:id])
    Observacion.delete(@observacion)
    @cita.observacions.delete(@observacion)
      respond_to do |format|
      format.html { redirect_to paciente_citum_observacions_path(@paciente,@cita), notice: 'Observación fue eliminada exitosamente.' }
      format.json { head :no_content }
    end
  end
  
  
  private
  
    def observacion_params
      params.require(:observacion).permit(:descripcion)
    end
    
end
