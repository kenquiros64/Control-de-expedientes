class MedicosController < ApplicationController
  before_filter :authenticate_medico!
  before_filter :must_be_admin

  def show
    @medico = Medico.find(params[:id])

    respond_to do |format|
        format.html # show.html.erb
        format.xml { render :xml => @medico }
    end
  end
  
  def index 
    @medicos = Medico.paginate(page: params[:page], :per_page => 25).all 
  end
  
  def destroy
    @medico = Medico.find(params[:id])
    @medico.destroy
    respond_to do |format|
      format.html { redirect_to medicos_url, notice: 'Médico fue eliminado exitosamente.' }
      format.json { head :no_content }
    end
  end
  
  def edit
    @medico = Medico.find(params[:id])
  end
  
  
  def update
    @medico = Medico.find(params[:id])
    respond_to do |format|
      if @medico.update(medico_params)
        format.html { redirect_to @medico, notice: 'Médico fue actualizado exitosamente.' }
        format.json { render :show, status: :ok, location: @paciente }
      else
        format.html { render :edit }
        format.json { render json: @medico.errors, status: :unprocessable_entity }
      end
    end
  end
  
  private
  
  def must_be_admin
    if !current_medico.has_role? :admin
      redirect_to root_path, notice: "Ingreso solo para administradores"
    end
  end
  
  def medico_params
      params.require(:medico).permit(:cedula, :nombre, :apellido1, :apellido2, :nacionalidad, :activo, :codigo)
  end
    
  
end
