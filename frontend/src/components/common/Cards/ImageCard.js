import React, { Component } from 'react';
import { Text, View } from 'react-native';
import { Card, Button, Icon } from 'react-native-elements'

class ImageCard extends Component{
  constructor(props){
    super(props)
  }

  render(){
    return (
      <Card
        title={this.props.title}
        image={this.props.image}>
        <Text style={{marginBottom: 10}}>{this.props.text}</Text>
        <View style={{flexDirection: 'row', flex: 2, justifyContent: 'space-between'}}>
          <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='ADD EVENT'/>
        <Button
        icon={<Icon name='code' color='#ffffff'/>}
        title='DELETE EVENT'/></View>
      </Card>
    );
  }
}

export default ImageCard;
